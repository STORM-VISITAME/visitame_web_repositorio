<%
 String contextPath = request.getContextPath();
 String contextST = contextPath+"ST/";
 String dirStatic = "resources";
%>
<!doctype html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes">

    <title>Visítame</title>
    <meta name="description" content="App de Visitas BBVA">
	
	<!-- Seria: /visitameST/ -->
    <base id = "baseUrl" href="<%=contextST%>">
    
    <!-- Seria: /visitame/services -->
    <!-- base de urls de servicos json -->
    <base id = "baseRest"  href="<%=contextPath%>/services/">

    <link rel="icon" href="<%=contextST%><%=dirStatic%>/images/favicon_visitame.ico">

    <!-- See https://goo.gl/OOhYW5 -->
    <link rel="manifest" href="<%=contextST%><%=dirStatic%>/manifest.json">

    <!-- See https://goo.gl/qRE0vM -->
    <meta name="theme-color" content="#3f51b5">

    <!-- Add to homescreen for Chrome on Android. Fallback for manifest.json -->
    <meta name="mobile-web-app-capable" content="yes">
    <meta name="application-name" content="Visitame App">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    <meta name="apple-mobile-web-app-title" content="Visitame App">

    <script>
    // Register the base URL
    const baseUrl = document.querySelector('#baseUrl').href;
    const baseRest = document.querySelector('#baseRest').href;
    const dirStatic = '<%=dirStatic%>';
    const contextST = '<%=contextST%>';
    const IP_REMOTE = '<%=request.getRemoteAddr()%>';
    // Load and register pre-caching Service Worker
    if ('serviceWorker' in navigator) {
      window.addEventListener('load', function() {
        navigator.serviceWorker.register('<%=contextST%><%=dirStatic%>/service-worker.js');
      });
    }
    </script>
    <script src="<%=contextST%><%=dirStatic%>/bower_components/webcomponentsjs/webcomponents-loader.js"></script>

    <link rel="import" href="<%=contextST%><%=dirStatic%>/src/visitame-app/visitame-app.html">
    <link rel="import" href="<%=contextST%><%=dirStatic%>/bower_components/re-captcha/re-captcha.html">
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDqyFz0Q9ZZLMmgqJhck4soRfa0V1s71jQ&libraries=places"></script>
   
   	<script src="<%=contextST%><%=dirStatic%>/bower_components/sweetalert2/dist/sweetalert2.min.js"></script>
   	<script src="<%=contextST%><%=dirStatic%>/bower_components/moment/min/moment.min.js"></script>
	<link rel="stylesheet" href="<%=contextST%><%=dirStatic%>/bower_components/sweetalert2/dist/sweetalert2.min.css">

	<!-- Include a polyfill for ES6 Promises (optional) for IE11 and Android browser -->
	<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script> -->

    <style>
      body {
        margin: 0;
        font-family: 'Roboto', 'Noto', sans-serif;
        line-height: 1.5;
        min-height: 100vh;
        background-color: #fff;
        position: relative;
      }
            
    </style>
    
  </head>
  <body>
    <visitame-app>
    	<div style="width: 100%; text-align: center;">
    		<re-captcha id="recaptchaLogin" sitekey="6Lfd7CcUAAAAALern9JHrjPXMg1YsKRj2LID6MI6" class="captcha"></re-captcha>
    	</div>
    </visitame-app>
    <view-error-dialog id="dialog-error"></view-error-dialog>	
  </body>
  <script>

  	function SyncObjects(callback){
  		var verify = setInterval(() => { 
				try{
					callback();
					clearInterval(verify);
				}catch(e){}
		  }, 100);
  	}
  
  	function mostrarError(mapaMensaje) {
		var SEVERIDAD_ALTA 	= "ALTA";
		var SEVERIDAD_MEDIA = "MEDIA";
		
		var ventaMensaje = document.getElementById('mensaje-error');
		var dialogError = document.getElementById('dialog-error');
		document.getElementById('mensaje_texto').innerHTML = mapaMensaje.mensajeError;
		dialogError.severidad = mensaje.severidad;
		
		var tipoSeveridad = mapaMensaje.severidad;
		if(tipoSeveridad == SEVERIDAD_ALTA){
			dialogError.estiloAlerta = "alerta-alta";
			dialogError.iconoAlerta = "icons:cancel"
		}else if(tipoSeveridad == SEVERIDAD_MEDIA){
			dialogError.estiloAlerta = "alerta-media";
			dialogError.iconoAlerta = "icons:report-problem";
		}else{
			dialogError.estiloAlerta = "alerta-baja";
			dialogError.iconoAlerta = "icons:report";
		}
		ventaMensaje.open();
	}
  	
  	function recargar(){
  		var ventaMensaje = document.getElementById('default');
  		ventaMensaje.reset();
  	}
  	
  	function scaleReCaptcha(){
  	if (document.getElementsByClassName('captcha').length > 0)
  	{parentWidth = document.getElementsByClassName('captcha')[0].clientWidth;
  	         childWidth = document.getElementsByClassName('captcha')[0].firstChild.scrollWidth;
  	         scale = (parentWidth)/304;
  	         if(scale < 1.1){
  	         	new_width = childWidth * scale;
  	         	document.getElementsByClassName('captcha')[0].style.transform = 'scale(' + scale + ')';
  	         	document.getElementsByClassName('captcha')[0].style.transformOrigin = '0 0';
  	         }else{
  	        	document.getElementsByClassName('captcha')[0].style.paddingLeft = "10%";
  	         }
  	     }
  	}

  	

  </script>
</html>
