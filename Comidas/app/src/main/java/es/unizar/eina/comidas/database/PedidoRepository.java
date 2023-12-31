package es.unizar.eina.comidas.database;


import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PedidoRepository {

    private PedidoDao mPedidoDao;
    private LiveData<List<Pedido>> mAllPedidos;
    private final long TIMEOUT = 15000;


    Throwable mException;
    public Throwable getException() {
        return mException;
    }

    // Note that in order to unit test the PedidoRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public PedidoRepository(Context context) {
        ComidasRoomDatabase db = ComidasRoomDatabase.getDatabase(context);
        mPedidoDao = db.pedidoDao();
        mAllPedidos = mPedidoDao.getOrderedPedidos();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Pedido>> getAllPedidos() {
        return mAllPedidos;
    }


    private Boolean estadoCorrecto(String estado){
        String solicitado = "SOLICITADO";
        String preparado = "PREPARADO";
        String recogido = "RECOGIDO";
        if(estado.equals(solicitado) || estado.equals(preparado) || estado.equals(recogido)){
            return true;
        }
        return false;
    }

    /**
     * Verifica si un número de teléfono es válido.
     *
     * @param telefono El número de teléfono a verificar.
     * @return true si el número de teléfono es válido (longitud igual a 9), false de lo contrario.
     */
    private Boolean telefonoValido(String telefono){
        return telefono.length() == 9;
    }

    /**
     * Verifica si la recogida es posterior al pedido comparando las fechas y horas.
     *
     * @param fechaFormateadaMomentoActualLT La fecha formateada del momento actual.
     * @param fechaFormateadaRecogidaLT La fecha formateada de la recogida.
     * @param horaFormateadaMomentoActualLT La hora formateada del momento actual.
     * @param horaFormateadaRecogidaLT La hora formateada de la recogida.
     * @return true si la recogida es posterior al pedido, false de lo contrario.
     */
    private Boolean fechaActualEsAnterior(LocalDate fechaFormateadaMomentoActualLT,
                                             LocalDate fechaFormateadaRecogidaLT,
                                             LocalTime horaFormateadaMomentoActualLT,
                                             LocalTime horaFormateadaRecogidaLT){
        Boolean valor = true;
        Boolean fechaActualEsAnterior = null;
        Boolean horaActualEsAnterior = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fechaActualEsAnterior = fechaFormateadaMomentoActualLT.isBefore(fechaFormateadaRecogidaLT);
            horaActualEsAnterior = horaFormateadaMomentoActualLT.isBefore(horaFormateadaRecogidaLT);
        }
        if(!fechaActualEsAnterior || (fechaActualEsAnterior && !horaActualEsAnterior)){
            valor = false;
        }
        return valor;
    }

    /**
     * Verifica si la recogida está programada para un lunes.
     *
     * @param fecha La fecha y hora formateada de la recogida.
     * @return true si la recogida está programada para un lunes, false de lo contrario.
     */
    private Boolean recogidaEnLunes(LocalDate fecha){
        Boolean valor = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            DayOfWeek day = fecha.getDayOfWeek();
            if (day.getValue() == 1) {
                valor = true;
            }
        }
        return valor;
    }

    /**
     * Verifica si la recogida está dentro del horario correcto.
     *
     * @param horaFormateadaRecogidaLT La hora formateada de la recogida.
     * @return true si la recogida está dentro del horario correcto, false de lo contrario.
     */
    private Boolean recogidaEnHorarioCorrecto(LocalTime horaFormateadaRecogidaLT){
        DateTimeFormatter formatterHora = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            String inicioEntregas = "19:30";
            String finEntregas = "23:00";

            LocalTime inicioEntregasLDT = LocalTime.parse(inicioEntregas, formatterHora);
            LocalTime finEntregasLDT = LocalTime.parse(finEntregas, formatterHora);

            if(horaFormateadaRecogidaLT.isAfter(inicioEntregasLDT)) {
                if (horaFormateadaRecogidaLT.isBefore(finEntregasLDT)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica si una cadena representa una fecha en formato correcto (yyyy/MM/dd).
     *
     * @param fecha La cadena que representa la fecha a verificar.
     * @return true si la cadena representa una fecha en formato correcto, false de lo contrario.
     */
    private Boolean formatoFechaCorrecto(String fecha){
        // Definir el formato esperado
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        formatoFecha.setLenient(false); // Desactivar la tolerancia a fechas inválidas

        try {
            // Intentar parsear la fecha
            formatoFecha.parse(fecha);
            return true; // La fecha es válida
        } catch (ParseException e) {
            // La excepción se lanza si la fecha no tiene el formato esperado
            return false; // La fecha no es válida
        }
    }

    /**
     * Verifica si una cadena representa una hora en formato correcto (HH:mm).
     *
     * @param hora La cadena que representa la hora a verificar.
     * @return true si la cadena representa una hora en formato correcto, false de lo contrario.
     */
    public static boolean formatoHoraCorrecto(String hora) {
        // Patrón de expresión regular para el formato deseado "HH:mm"
        String patronFormato = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

        Pattern patron = Pattern.compile(patronFormato);
        Matcher matcher = patron.matcher(hora);

        // Verificar si el formato coincide
        return matcher.matches();
    }

    private Boolean validarPedido(Pedido pedido) {
        Boolean valor = true;

        //Valores del pedido
        String fecha = pedido.getFechaRecogida();
        String hora = pedido.getHoraRecogida();
        String nombreCliente = pedido.getNombreCliente();
        Integer telefono = pedido.getTelefonoCliente();
        String estado = pedido.getEstado();
        Double precio = pedido.getPrecioPedido();

        LocalDate fechaFormateadaMomentoActualLT = null;
        LocalDate fechaFormateadaRecogidaLT = null;
        LocalTime horaFormateadaMomentoActualLT = null;
        LocalTime horaFormateadaRecogidaLT = null;
        LocalDateTime fechaFormateadaRecogidaLDT = null;

        if(fecha == null || hora == null || nombreCliente == null || telefono == null ||
                estado == null || precio == null){
            valor = false;
        } else if(!formatoFechaCorrecto(fecha)){
            valor = false;
        }
        if(!formatoHoraCorrecto(hora)){
            valor = false;
        }
        if(valor == false){
            return false;
        }

        //Formatos
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String fechaRecogida = fecha;

            //Fecha
            DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime momentoActualFecha = LocalDateTime.now();
            String fechaFormateadaMomentoActual = momentoActualFecha.format(formatterFecha);
            fechaFormateadaMomentoActualLT = LocalDate.parse(fechaFormateadaMomentoActual, formatterFecha);
            fechaFormateadaRecogidaLT = LocalDate.parse(fechaRecogida, formatterFecha);
            //fechaFormateadaRecogidaLDT = LocalDateTime.parse(fechaRecogida, formatterFecha);

            //Hora
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime momentoActualHora = LocalDateTime.now();
            String horaFormateadaMomentoActual = momentoActualHora.format(formatterHora);
            horaFormateadaMomentoActualLT = LocalTime.parse(horaFormateadaMomentoActual, formatterHora);
            horaFormateadaRecogidaLT = LocalTime.parse(hora, formatterHora);
        }

        if (nombreCliente.isEmpty() || estado.isEmpty() || fecha.isEmpty() ||
                TextUtils.isEmpty(hora)) {
            valor = false;
        } else if (!estadoCorrecto(estado)) {
            valor = false;
        } else if(!telefonoValido(telefono.toString())){
            valor = false;
        } else if(!fechaActualEsAnterior(fechaFormateadaMomentoActualLT,
                fechaFormateadaRecogidaLT, horaFormateadaMomentoActualLT,
                horaFormateadaRecogidaLT)){
            valor = false;
        } else if(recogidaEnLunes(fechaFormateadaRecogidaLT)) {
            valor = false;
        } else if(!recogidaEnHorarioCorrecto(horaFormateadaRecogidaLT)){
            valor = false;
        }
        return valor;
    }


    /** Inserta un pedido
     * @param pedido
     * @return un valor entero largo con el identificador del pedido que se ha creado.
     */
    public long insert(Pedido pedido) {
        AtomicLong result = new AtomicLong();
        Semaphore resource = new Semaphore(0);

        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            long value;
            try {
                if (validarPedido(pedido)) {
                    value = mPedidoDao.insert(pedido);
                    if (value == -1) {
                        throw new RuntimeException("Error al insertar el pedido. El valor devuelto fue -1.");
                    }
                } else {
                    throw new RuntimeException("Error al insertar el pedido. Validación del pedido fallida.");
                }
                result.set(value);
                resource.release();
            } catch (Throwable throwable) {
                mException = throwable; // Almacenar la excepción
            }
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            Log.d("PedidoRepository", "InterruptedException en Insert: " + e.getMessage());
        }
        return result.get();
    }

    /** Modifica un pedido
     * @param pedido
     * @return un valor entero con el número de filas modificadas.
     */
    public int update(Pedido pedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);
        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            int value;
            try {
                if (validarPedido(pedido)) {
                    value = mPedidoDao.update(pedido);
                    if (value == -1) {
                        throw new RuntimeException("Error al modificar el pedido. El valor devuelto fue -1.");
                    }
                    else if (value == 0){
                        throw new RuntimeException("Error al modificar el pedido. El valor devuelto fue 0.");
                    }
                } else {
                    throw new RuntimeException("Error al modificar el pedido. Validación del pedido fallida.");
                }
                result.set(value);
                resource.release();
            } catch (Throwable throwable) {
                mException = throwable; // Almacenar la excepción
            }
        });
        try{
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        }catch (InterruptedException e){
            Log.d("PedidoRepository", "InterruptedExecution: " + e.getMessage());
        }
        return result.get();
    }

    /** Elimina un pedido
     * @param pedido
     * @return un valor entero con el número de filas eliminadas.
     */
    public int delete(Pedido pedido) {
        AtomicInteger result = new AtomicInteger();
        Semaphore resource = new Semaphore(0);

        ComidasRoomDatabase.databaseWriteExecutor.execute(() -> {
            int value;
            try {
                value = mPedidoDao.delete(pedido);
                if (value == -1) {
                    throw new RuntimeException("Error al eliminar el pedido. El valor devuelto fue -1.");
                }
                else if (value == 0){
                    throw new RuntimeException("Error al eliminar el pedido. El valor devuelto fue 0.");
                }
                result.set(value);
                resource.release();
            } catch (Throwable throwable) {
                mException = throwable; // Almacenar la excepción
            }
        });
        try {
            resource.tryAcquire(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            Log.d("PedidoRepository", "InterruptedException en Insert: " + e.getMessage());
        }
        return result.get();
    }
}
