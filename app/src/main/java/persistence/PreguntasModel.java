package persistence;

import android.content.Context;

public class PreguntasModel {

	private String idpregunta;
	private String pregunta;
	private String nickname;
	private String fechaPregunta;
	private String respuesta;
	private String fechaRespuesta;
	
	public PreguntasModel(Context context, String idpregunta,
			String pregunta, String nickname, String fechaPregunta, String respuesta,
			String fechaRespuesta) {
		this.idpregunta = idpregunta;
		this.pregunta = pregunta;
		this.fechaPregunta = fechaPregunta;
		this.respuesta = respuesta;
		this.fechaRespuesta = fechaRespuesta;
		this.nickname = nickname;
	}

	public String getIdpregunta() {
		return idpregunta;
	}

	public String getPregunta() {
		return pregunta;
	}

	public String getNickName(){
		return nickname;
	}
	
	public String getFechaPregunta() {
		return fechaPregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public String getFechaRespuesta() {
		return fechaRespuesta;
	}


}
