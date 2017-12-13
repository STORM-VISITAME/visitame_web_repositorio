<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, minimum-scale=1, initial-scale=1, user-scalable=yes">
<title>Pruebas de conexion</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://bootswatch.com/4/yeti/bootstrap.min.css">
	
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<style>
body {
	margin: 15px;
}

.tab-content {
	border-bottom: 1px #e1e1e1 solid;
	border-right: 1px #e1e1e1 solid;
	border-left: 1px #e1e1e1 solid;
	padding: 15px;
}

.titulo {
	border-bottom: 1px #e1e1e1 solid;
	padding-bottom: 5px;
	margin-bottom: 20px;
	margin-top: 5px;
}

.loading{
	width: 100%;text-align: center;
}

.loading i{
	color: #1e88e5;
	font-size: 2em;
}

#responseQueryContent table>tbody{
	max-height: 200px;
	overflow-y:auto;
}

</style>

</head>
<body>

	<div class="container">
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item"><a class="nav-link active" href="#profile"
				role="tab" data-toggle="tab">Test conectividad</a></li>
			<li class="nav-item"><a class="nav-link" href="#buzz" role="tab"
				data-toggle="tab">Caja Magica</a></li>
			<li class="nav-item"><a class="nav-link" href="#references"
				role="tab" data-toggle="tab">Consultas</a></li>
		</ul>

		<!-- Tab panes -->
		<div class="tab-content">

			<div role="tabpanel" class="tab-pane active" id="profile">
			<h4 class="titulo">Telnet</h4>
				<form>
					<div class="row">

						<div class="col-sm-12 col-lg-8">
							<input type="text" id = "ip" class="form-control" placeholder=":IP">
						</div>
						<div class="col-sm-12 col-lg-2">
							<input type="text" id = "puerto" class="form-control" placeholder=":PUERTO">
						</div>
						<div class="col-sm-12 col-lg-2">
							<button type="button" class="btn btn-primary" id="btnTelnet" style="width: 100%;">Probar</button>
						</div>

					</div>
				</form>

				<div class="row" style="margin-top: 15px;">
					<div class="col-12" id = "contentMessageTelnet">
						
					</div>
				</div>
				
				
				<h4 class="titulo">Ping</h4>
				<form>
					<div class="row">

						<div class="col-sm-12 col-lg-10">
							<input type="text" id = "host" class="form-control" placeholder=":IP ó HOST">
						</div>
						<div class="col-sm-12 col-lg-2">
							<button type="button" class="btn btn-warning" id="btnPing" style="width: 100%;">Probar</button>
						</div>

					</div>
				</form>

				<div class="row" style="margin-top: 15px;">
					<div class="col-12" id = "contentMessagePing">
						
					</div>
				</div>
				
			</div>
			<div role="tabpanel" class="tab-pane" id="buzz">
			
				<iframe src = "http://localhost/resources/magicbox/" frameborder="0" width="100%" height="510px"></iframe>
			
			</div>
			<div role="tabpanel" class="tab-pane" id="references">
			
				<div class="form-group">
			      <textarea class="form-control" id="querySql" rows="7" placeholder = "CONSULTA SQL" ></textarea>
			    </div>
			    <div class="row">
			        <div class="col-12">
				        <select class="form-control float-left" id="tipoQuery" style="width: 115px">
					        <option value="select" >SELECT</option>
					        <option value="script" >SCRIPT</option>
					    </select>
				        <a href="#" class="btn btn-primary float-right" id = "btnExecuteQuery" >Ejecutar</a>
				        <a href="#" class="btn btn-warning float-right" id = "btnLimpiar" >Limpiar</a>
			        </div>
			    </div>
			    
			    <div  id = "responseQueryContent" style="margin-top: 15px;">
		        
			    </div>
			
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.min.js" ></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
		integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
		crossorigin="anonymous"></script>
		
	<script type="text/javascript">

	(function(){
		var me = this;

		me.clsDanger  = 'danger';
		me.clsSuccess = 'success';

		me.init = function(){
			$('#btnTelnet').click(function(){
				me.loading('contentMessageTelnet');
				$.ajax({
				  method: "POST",
				  url: "/visitame/tests/telnet",
				  dataType:'json',
				  data: { ip: $('#ip').val() , puerto : $('#puerto').val() }
				})
			    .done(function( data ) {
			    	 if(data.success){
			    		 me.message('contentMessageTelnet',me.clsSuccess,'<strong>Conexion establecida!</strong> IP: '+$('#ip').val()+' por el PUERTO: '+$('#puerto').val());
				     }else{
				    	 me.message('contentMessageTelnet',me.clsDanger,'<strong>Sin conexión!</strong> IP: '+$('#ip').val()+' por el PUERTO: '+$('#puerto').val());
					 }
			    });
			});

			$('#btnPing').click(function(){
				me.loading('contentMessagePing');
				$.ajax({
				  method: "POST",
				  url: "/visitame/tests/ping",
				  dataType:'json',
				  data: { ip: $('#host').val()  }
				})
			    .done(function( data ) {
			    	 if(data.success){
			    		 me.message('contentMessagePing',me.clsSuccess,'<strong>Ping establecido a: </strong>'+$('#host').val());
				     }else{
				    	 me.message('contentMessagePing',me.clsDanger,'<strong>Ping fallido!</strong> Se intentó realizar ping a: '+$('#host').val());
					 }
			    });
			});

			$('#btnExecuteQuery').click(me.executeQuery);
			$('#btnLimpiar').click(me.limpiarQueryExecutor);
			
		} 

		me.message = function(idContent,type,message){
			var msg =  '<div class="alert alert-dismissible alert-'+type+'">'+
							'<button type="button" class="close" data-dismiss="alert">&times;</button>'+
							message+
						'</div>';
						
			$('#'+idContent).html(msg);
		}

		me.executeQuery = function(){
			console.log('executeQuery');
			if($('#querySql').val()){
				console.log($('#querySql').val());
			}
			console.log($('#tipoQuery').val());

			me.loading('responseQueryContent');
			$.ajax({
			  method: "POST",
			  url: "/visitame/tests/query",
			  dataType:'json',
			  data: { query: $('#querySql').val() , tipo : $('#tipoQuery').val() }
			})
		    .done(function( response ) {
		    	me.unLoading('responseQueryContent');
				console.log(response);
		    	 if(response.success){
		    		 me.buildSuccessResponse(response);
			     }else{
			    	 me.message('responseQueryContent',me.clsDanger,response.message);
				 } 
		    });
			
		}

		me.buildSuccessResponse = function(response){
			if($('#tipoQuery').val() == 'select'){
				me.buildTableResult(response);
			}else if($('#tipoQuery').val() == 'script'){
				me.buildResponseScript(response);
			}
		}

		me.buildResponseScript = function(response){
			me.message('responseQueryContent',me.clsSuccess,'Consulta ejecutado de forma correcta.');
		}

		me.buildTableResult = function(response){

			var columns = response.columns;
			var data = response.data;

			var htmlTable = '<table class="table table-responsive table-striped table-bordered table-hover" >';

			//Construccion de headers columns
			htmlTable+= '<thead class="thead-inverse">';
			htmlTable+= '<tr>';
			columns.forEach(function(column){
				htmlTable+= '<th>'+column+'</th>';
			});
			htmlTable+= '</tr>';
			htmlTable+= '</thead>';
			
			//Construccion de las filas del body
			htmlTable+= '<tbody>';
			data.forEach(function(row){
				htmlTable+= '<tr>';
				columns.forEach(function(column){
					htmlTable+= '<td>'+row[column]+'</td>';
				});
		        htmlTable+= '</tr>';
			})
			htmlTable+= '</tbody>';
			
			htmlTable+= '</table>';
			$('#responseQueryContent').html(htmlTable);
			
		}

		me.limpiarQueryExecutor = function(){
			$('#querySql').val('');
			$('#responseQueryContent').empty();
		}

		me.loading = function(idContent){
			$('#'+idContent).html('<div class="loading" ><i class="fa fa-circle-o-notch fa-spin"></i></div>');
		}
		me.unLoading = function(idContent){
			$('#'+idContent).html('');
		}

		this.init();
	})();
	
	</script>	

</body>
</html>