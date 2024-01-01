package es.unizar.eina.comidas.T234_Pedidos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.unizar.eina.T234_Comidas.R;

/** Pantalla utilizada para la creación o edición de un pedido */
public class PedidoEdit extends AppCompatActivity {

    public static final String PEDIDO_NOMBRE_CLIENTE = "nombreClientePedido";
    public static final String PEDIDO_TELEFONO_CLIENTE = "telefonoClientePedido";
    public static final String PEDIDO_ESTADO = "estadoPedido";
    public static final String PEDIDO_FECHA_RECOGIDA = "fechaPedidoRecogida";
    public static final String PEDIDO_HORA_RECOGIDA = "horaPedidoRecogida";
    public static final String PEDIDO_PRECIO = "precioPedido";
    public static final String PEDIDO_ID = "id";

    //Lista de platos y raciones ??

    private EditText mNombreClienteText;
    private EditText mTelefonoClienteText;
    private EditText mEstadoText;
    private EditText mFechaRecogidaText;
    private EditText mHoraRecogidaText;

    private EditText mPrecioPedidoText;

    private Integer mRowId;

    Button mSaveButton;

    PedidoViewModel mPedidoViewModel;

    //Errores
    public static final int RESULT_CANCELED_HUECOS = 2;
    public static final int RESULT_CANCELED_ESTADO = 3;
    public static final int RESULT_CANCELED_TELEFONO = 4;
    public static final int RESULT_CANCELED_MOMENTO_RECOGIDA = 5;
    public static final int RESULT_CANCELED_LUNES_RECOGIDA = 6;
    public static final int RESULT_CANCELED_FUERA_DE_HORARIO = 7;
    public static final int RESULT_CANCELED_FORMATO_FECHA = 8;
    public static final int RESULT_CANCELED_FORMATO_HORA = 9;

    public static int RESULT_ACTIVIDAD;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidoedit);
        mPedidoViewModel = new ViewModelProvider(this).get(PedidoViewModel.class);

        mNombreClienteText = (EditText) findViewById(R.id.nombreClientePedido);
        mTelefonoClienteText = (EditText) findViewById(R.id.telefonoClientePedido);
        mEstadoText = (EditText) findViewById(R.id.estadoPedido);
        mFechaRecogidaText = (EditText) findViewById(R.id.fechaPedidoRecogida);
        mHoraRecogidaText = (EditText) findViewById(R.id.horaPedidoRecogida);
        mPrecioPedidoText = (EditText) findViewById(R.id.precioPedido);

        mSaveButton = findViewById(R.id.button_pedidos);
        mSaveButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (!validarPedido()){
                setResult(RESULT_ACTIVIDAD, replyIntent);
            } else {
                String nombreCliente = (String) mNombreClienteText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_NOMBRE_CLIENTE, nombreCliente);
                Integer telefono = Integer.parseInt(mTelefonoClienteText.getText().toString());
                replyIntent.putExtra(PedidoEdit.PEDIDO_TELEFONO_CLIENTE, telefono);
                String estado = (String) mEstadoText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_ESTADO, estado);
                String fecha = mFechaRecogidaText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_FECHA_RECOGIDA, fecha);
                String hora = mHoraRecogidaText.getText().toString();
                replyIntent.putExtra(PedidoEdit.PEDIDO_HORA_RECOGIDA, hora);
                Double precioPedido = Double.parseDouble(calcularPrecioPedido());
                replyIntent.putExtra(PedidoEdit.PEDIDO_PRECIO, precioPedido);
                if (mRowId != null) {
                    replyIntent.putExtra(PedidoEdit.PEDIDO_ID, mRowId.intValue());
                }
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
        populateFields();

    }

    private void populateFields () {
        mRowId = null;
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            mNombreClienteText.setText(extras.getString(PedidoEdit.PEDIDO_NOMBRE_CLIENTE));
            Integer telefono = extras.getInt(PedidoEdit.PEDIDO_TELEFONO_CLIENTE);
            mTelefonoClienteText.setText(telefono.toString());
            mEstadoText.setText(extras.getString(PedidoEdit.PEDIDO_ESTADO));
            mFechaRecogidaText.setText(extras.getString(PedidoEdit.PEDIDO_FECHA_RECOGIDA));
            mHoraRecogidaText.setText(extras.getString(PedidoEdit.PEDIDO_HORA_RECOGIDA));
            Double precio = extras.getDouble(PedidoEdit.PEDIDO_PRECIO);
            mPrecioPedidoText.setText(precio.toString());
            mRowId = extras.getInt(PedidoEdit.PEDIDO_ID);
        }
    }

    private String calcularPrecioPedido(){
        double precio = 20;

        return precio + "";
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
    private Boolean recogidaPosteriorAPedido(LocalDate fechaFormateadaMomentoActualLT,
                                             LocalDate fechaFormateadaRecogidaLT,
                                             LocalTime horaFormateadaMomentoActualLT,
                                             LocalTime horaFormateadaRecogidaLT){
        Boolean valor = true;
        Boolean fechaActualEsAnterior = null;
        Boolean fechaActualEsIgual = null;
        Boolean horaActualEsAnterior = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fechaActualEsAnterior = fechaFormateadaMomentoActualLT.isBefore(fechaFormateadaRecogidaLT);
            fechaActualEsIgual = fechaFormateadaMomentoActualLT.isEqual(fechaFormateadaRecogidaLT);
            horaActualEsAnterior = horaFormateadaMomentoActualLT.isBefore(horaFormateadaRecogidaLT);
        }
        if(!fechaActualEsAnterior || (fechaActualEsIgual && !horaActualEsAnterior)){
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

    private Boolean validarPedido() {
        Boolean valor = true;

        LocalDate fechaFormateadaMomentoActualLT = null;
        LocalDate fechaFormateadaRecogidaLT = null;
        LocalTime horaFormateadaMomentoActualLT = null;
        LocalTime horaFormateadaRecogidaLT = null;
        LocalDateTime fechaFormateadaRecogidaLDT = null;

        if(!formatoFechaCorrecto(mFechaRecogidaText.getText().toString())){
            RESULT_ACTIVIDAD = RESULT_CANCELED_FORMATO_FECHA;
            valor = false;
            return valor;
        }
        if(!formatoHoraCorrecto(mHoraRecogidaText.getText().toString())){
            RESULT_ACTIVIDAD = RESULT_CANCELED_FORMATO_HORA;
            valor = false;
            return valor;
        }

        //Formatos
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String fechaRecogida = mFechaRecogidaText.getText().toString();

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
            horaFormateadaRecogidaLT = LocalTime.parse(mHoraRecogidaText.getText().toString(), formatterHora);
        }

        if (TextUtils.isEmpty(mNombreClienteText.getText()) ||
                TextUtils.isEmpty(mTelefonoClienteText.getText()) ||
                TextUtils.isEmpty(mEstadoText.getText()) ||
                TextUtils.isEmpty(mFechaRecogidaText.getText()) ||
                TextUtils.isEmpty(mHoraRecogidaText.getText())) {
            RESULT_ACTIVIDAD = RESULT_CANCELED_HUECOS;
            valor = false;
        } else if (!estadoCorrecto(mEstadoText.getText().toString())) {
            RESULT_ACTIVIDAD = RESULT_CANCELED_ESTADO;
            valor = false;
        } else if(!telefonoValido(mTelefonoClienteText.getText().toString())){
            RESULT_ACTIVIDAD = RESULT_CANCELED_TELEFONO;
            valor = false;
        } else if(!recogidaPosteriorAPedido(fechaFormateadaMomentoActualLT,
                  fechaFormateadaRecogidaLT, horaFormateadaMomentoActualLT,
                  horaFormateadaRecogidaLT)){
            RESULT_ACTIVIDAD = RESULT_CANCELED_MOMENTO_RECOGIDA;
            valor = false;
        } else if(recogidaEnLunes(fechaFormateadaRecogidaLT)) {
            RESULT_ACTIVIDAD = RESULT_CANCELED_LUNES_RECOGIDA;
            valor = false;
        } else if(!recogidaEnHorarioCorrecto(horaFormateadaRecogidaLT)){
            RESULT_ACTIVIDAD = RESULT_CANCELED_FUERA_DE_HORARIO;
            valor = false;
        }
        return valor;
    }

}
