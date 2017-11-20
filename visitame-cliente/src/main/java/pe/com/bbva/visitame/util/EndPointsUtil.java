package pe.com.bbva.visitame.util;

public final class EndPointsUtil {

	public static class HTTP_METODOS {
		public final static String POST		=	"POST";
		public final static String GET		=	"GET";
		public final static String PUT		=	"PUT";
		public final static String PATCH	=	"PATCH";
		public final static String DELETE	=	"DELETE";
	}
	
	public static class END_POINTS_CONFIGURACION {
		public final static String VALORES_BY_COD_LISTA 			=	"/configuracion/valores";
		public final static String VALORES_BY_COD_LISTA_RESPUESTA 	=	"/configuracion/valoresRespuesta";
		public final static String PARAMETROS			 			=	"/configuracion/parametro";
		public final static String VALORES_MOTIVO			 		=	"/configuracion/valoresMotivo";
	}
	
	public static class END_POINTS_ACCOUNT {
		public final static String GET_CUSTOMER 					=	"/account/getCustomer";
		public final static String VALIDAR_USUARIO 					=	"/account/validarUsuario";
		public final static String ACTUALIZAR_DATOS_CONTACTO		=	"/account/actualizarDatosContacto";
	}
	
	public static class END_POINTS_GEOLOCALIZACION {
		public final static String GET_POIS 						=	"/geolocalizacion/pois";
	}
	
	public static class END_POINTS_TICKET {
		public final static String GENERATE_TICKET					=	"/oficina/ticket";
	}
	
}
