package presentacion.controlador;

public class Evento {

	public static final int ALTA_AULA = 100;
	public static final int BAJA_AULA = 101;
	public static final int MODIFICAR_AULA = 102;
	public static final int LISTAR_AULAS = 103;
	public static final int MOSTRAR_AULA_POR_ID = 104;
	public static final int LISTAR_AULAS_NO_DISPONIBLES = 105;

	public static final int ALTA_AULA_OK = 400;
	public static final int ALTA_AULA_ERROR = 401;
	public static final int LISTAR_AULAS_OK = 402;
	public static final int LISTAR_AULAS_NO_DISPONIBLES_OK = 403;
	public static final int BAJA_AULA_OK = 404;
	public static final int BAJA_AULA_ERROR = 405;
	public static final int MODIFICAR_AULA_OK = 406;
	public static final int MODIFICAR_AULA_ERROR = 407;
	public static final int MOSTRAR_AULA_POR_ID_OK = 408;
	public static final int MOSTRAR_AULA_POR_ID_ERROR = 409;

	public static final int ALTA_ENTRENADOR = 106;
	public static final int ALTA_MANTENIMIENTO = 107;
	public static final int LISTAR_PERSONAL_NO_DISPONIBLE = 108;
	public static final int BAJA_PERSONAL = 109;
	public static final int MODIFICAR_ENTRENADOR = 110;
	public static final int MODIFICAR_MANTENIMIENTO = 11;
	public static final int LISTAR_PERSONAL = 112;
	public static final int MOSTRAR_PERSONAL_POR_ID = 113;

	public static final int ALTA_ENTRENADOR_OK = 410;
	public static final int ALTA_ENTRENADOR_ERROR = 411;
	public static final int ALTA_MANTENIMIENTO_OK = 412;
	public static final int ALTA_MANTENIMIENTO_ERROR = 413;
	public static final int BAJA_PERSONAL_OK = 414;
	public static final int BAJA_PERSONAL_ERROR = 415;
	public static final int LISTAR_PERSONAL_OK = 416;
	public static final int LISTAR_PERSONAL_ERROR = 417;
	public static final int LISTAR_PERSONAL_NO_DISPONIBLE_OK = 418;
	public static final int LISTAR_PERSONAL_NO_DISPONIBLE_ERROR = 419;
	public static final int MODIFICAR_ENTRENADOR_OK = 420;
	public static final int MODIFICAR_ENTRENADOR_ERROR = 421;
	public static final int MODIFICAR_MANTENIMIENTO_OK = 422;
	public static final int MODIFICAR_MANTENIMIENTO_ERROR = 423;
	public static final int MOSTRAR_PERSONAL_POR_ID_OK = 424;
	public static final int MOSTRAR_PERSONAL_POR_ID_ERROR = 425;

	public static final int ALTA_CLASE = 114;
	public static final int LISTAR_CLASES_NO_DISPONIBLES = 115;
	public static final int BAJA_CLASE = 116;
	public static final int LISTAR_CLASES = 117;
	public static final int MODIFICAR_CLASE = 118;
	public static final int MOSTRAR_CLASE_POR_ID = 119;
	public static final int MOSTRAR_CLASE_POR_AULA = 120;
	public static final int MOSTRAR_CLASE_POR_ENTRENADOR = 121;
	public static final int ASIGNAR_CLASE_A_AULA = 122;
	public static final int ASIGNAR_CLASE_A_ENTRENADOR = 123;

	public static final int ALTA_CLASE_OK = 426;
	public static final int ALTA_CLASE_ERROR = 427;
	public static final int LISTAR_CLASES_NO_DISPONIBLES_OK = 428;
	public static final int BAJA_CLASE_OK = 429;
	public static final int BAJA_CLASE_ERROR = 430;
	public static final int LISTAR_CLASES_OK = 431;
	public static final int MODIFICAR_CLASE_OK = 432;
	public static final int MODIFICAR_CLASE_ERROR = 433;
	public static final int MOSTRAR_CLASE_POR_ID_OK = 434;
	public static final int MOSTRAR_CLASE_POR_ID_ERROR = 435;
	public static final int MOSTRAR_CLASE_POR_AULA_OK = 436;
	public static final int MOSTRAR_CLASE_POR_AULA_ERROR = 437;
	public static final int MOSTRAR_CLASE_POR_ENTRENADOR_OK = 438;
	public static final int MOSTRAR_CLASE_POR_ENTRENADOR_ERROR = 439;
	public static final int ASIGNAR_CLASE_A_AULA_OK = 440;
	public static final int ASIGNAR_CLASE_A_AULA_ERROR = 441;
	public static final int ASIGNAR_CLASE_A_ENTRENADOR_OK = 442;
	public static final int ASIGNAR_CLASE_A_ENTRENADOR_ERROR = 443;

	public static final int ALTA_CLIENTE = 124;
	public static final int LISTAR_CLIENTES_NO_DISPONIBLES = 125;
	public static final int BAJA_CLIENTE = 126;
	public static final int LISTAR_CLIENTES = 127;
	public static final int MODIFICAR_CLIENTE = 128;
	public static final int MOSTRAR_CLIENTE_POR_ID = 129;

	public static final int ALTA_CLIENTE_OK = 444;
	public static final int ALTA_CLIENTE_ERROR = 445;
	public static final int LISTAR_CLIENTES_OK = 446;
	public static final int LISTAR_CLIENTES_ERROR = 447;
	public static final int LISTAR_CLIENTES_NO_DISPONIBLES_OK = 448;
	public static final int LISTAR_CLIENTES_NO_DISPONIBLES_ERROR = 449;
	public static final int BAJA_CLIENTE_OK = 450;
	public static final int BAJA_CLIENTE_ERROR = 451;
	public static final int MODIFICAR_CLIENTE_OK = 452;
	public static final int MODIFICAR_CLIENTE_ERROR = 453;
	public static final int MOSTRAR_CLIENTE_POR_ID_OK = 454;
	public static final int MOSTRAR_CLIENTE_POR_ID_ERROR = 455;
	
	public static final int ALTA_MATRICULA = 130;
	public static final int LISTAR_MATRICULAS_NO_DISPONIBLES = 131;
	public static final int BAJA_MATRICULA = 132;
	public static final int LISTAR_MATRICULAS = 133;
	public static final int MODIFICAR_MATRICULA = 134;
	public static final int MOSTRAR_MATRICULA_POR_ID_CLASE = 135;
	public static final int MOSTRAR_MATRICULA_POR_ID_CLIENTE = 136;

	public static final int ALTA_MATRICULA_OK = 456;
	public static final int ALTA_MATRICULA_ERROR = 457;
	public static final int LISTAR_MATRICULAS_OK = 458;
	public static final int LISTAR_MATRICULAS_ERROR = 459;
	public static final int LISTAR_MATRICULAS_NO_DISPONIBLES_OK = 460;
	public static final int LISTAR_MATRICULAS_NO_DISPONIBLES_ERROR = 461;
	public static final int BAJA_MATRICULA_OK = 462;
	public static final int BAJA_MATRICULA_ERROR = 463;
	public static final int MODIFICAR_MATRICULA_OK = 464;
	public static final int MODIFICAR_MATRICULA_ERROR = 465;
	public static final int MOSTRAR_MATRICULA_POR_ID_CLASE_OK = 466;
	public static final int MOSTRAR_MATRICULA_POR_ID_CLASE_ERROR = 467;
	public static final int MOSTRAR_MATRICULA_POR_ID_CLIENTE_OK = 468;
	public static final int MOSTRAR_MATRICULA_POR_ID_CLIENTE_ERROR = 469;

}
