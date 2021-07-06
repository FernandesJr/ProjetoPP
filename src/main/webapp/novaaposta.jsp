<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Usuario"  %>

<% 	
	Usuario usuario = (Usuario) request.getAttribute("usuario");
	
%>

<%if(request.getAttribute("msg") != null){%>
	<%=request.getAttribute("msg") %>
<%}%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Jogo Loteria - Nova Aposta</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link
    href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
    rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/style.css">
  <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">


</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion no-print" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="ControllerNovaAposta?action=dashboard&id=<%= usuario.getId() %>">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Jogo Loteria</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="ControllerNovaAposta?action=dashboard&id=<%= usuario.getId() %>">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Dashboard</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Menu Principal
      </div>

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link" href="#page-top">
          <i class="fas fa-fw fa-cube"></i>
          <span>Nova Aposta</span></a>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="ControllerApostas?id=<%= usuario.getId() %>">
          <i class="fas fa-fw fa-cubes"></i>
          <span>Apostas</span></a>
      </li>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-bell fa-fw"></i>
                <!-- Counter - Alerts -->
                <span class="badge badge-danger badge-counter">1+</span>
              </a>
              <!-- Dropdown - Alerts -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                <h6 class="dropdown-header">
                  Alertas
                </h6>
                <a class="dropdown-item d-flex align-items-center" href="#">
                  <div class="mr-3">
                    <div class="icon-circle bg-success">
                      <i class="fas fa-donate text-white"></i>
                    </div>
                  </div>
                  <div>
                    <div class="small text-gray-500">Hoje</div>
                    Seja bem-vindo ao Jogo Loteria! e boa Sorte! vai Precisar!
                  </div>
                </a>
                
                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
              </div>
            </li>

            <!-- Nav Item - Messages -->
            <li class="nav-item dropdown no-arrow mx-1">
              <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-envelope fa-fw"></i>
                <!-- Counter - Messages -->
                <!-- <span class="badge badge-danger badge-counter">7</span> -->
              </a>
              <!-- Dropdown - Messages -->
              <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                <h6 class="dropdown-header">
                  Mensagens
                </h6>
                
                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
              </div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=usuario.getNome() %></span>
                <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="ControllerEditarUser?id=<%=usuario.getId()%>">
                  <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                  Perfil
                </a> 
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  Sair
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800 text-center">Nova Aposta</h1>
          <div class="row">
            <div class="col-12">

              <!-- Inicio do Card -->
              <form action="ControllerNovaAposta" method="post">
	              <div class="card shadow mb-4 card-novaaposta mx-auto">
	                <div class="card-header py-3">
	                  <h6 class="m-0 font-weight-bold text-primary text-center">Selecione os <strong>( 5 )</strong> Números da Cartela</h6>
	                </div>
	                <div class="card-body">
	
	                  <div class="row">
	                    <div class="col-md-6 d-flex align-items-center justify-content-center">
	
	                      <div class="cartela ml-3">
	
	                        <div class="d-flex justify-content-center">
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="1" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox1">01</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="2" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox2">02</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox3" value="3" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox3">03</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox4" value="4" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox4">04</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox5" value="5" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox5">05</label>
	                          </div>
	
	                        </div>
	
	                        <div class="d-flex justify-content-center">
	
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox6" value="6" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox6">06</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox7" value="7" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox7">07</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox8" value="8" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox8">08</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox9" value="9" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox9">09</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox10" value="10" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox10">10</label>
	                          </div>
	
	                        </div>
	
	                        <div class="d-flex justify-content-center">
	
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox11" value="11" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox11">11</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox12" value="12" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox12">12</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox13" value="13" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox13">13</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox14" value="14" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox14">14</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox15" value="15" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox15">15</label>
	                          </div>
	
	                        </div>
	
	                        <div class="d-flex justify-content-center">
	
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox16" value="16" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox16">16</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox17" value="17" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox17">17</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox18" value="18" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox18">18</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox19" value="19" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox19">19</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox20" value="20" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox20">20</label>
	                          </div>
	
	                        </div>
	
	                        <div class="d-flex justify-content-center">
	
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox21" value="21" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox21">21</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox22" value="22" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox22">22</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox23" value="23" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox23">23</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox24" value="24" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox24">24</label>
	                          </div>
	                          <div class="form-check form-check-inline">
	                            <input class="form-check-input" type="checkbox" id="inlineCheckbox25" value="25" name="numEscolhido" onclick="selectNumber()">
	                            <label class="form-check-label" for="inlineCheckbox25">25</label>
	                          </div>
	
	                        </div>
	
	                      </div>
	
	                    </div>
	                    <div class="col-md-6">
	                      <img class="imagem-novaaposta img-fluid" alt="dinheiro"
	                        src="https://image.freepik.com/free-vector/abstract-illustration-stock-exchange-data_23-2148604352.jpg">
	                    </div>
	                  </div>
	                </div>
	                <!-- Seletor do valor da aposta -->
	                <div class="form-group col-md-4">
	      				<label for="inputState">Valor da aposta R$</label>
	      				<select id="inputState" class="form-control" name="valorAposta">
	        				<option selected>10.00</option>
	        				<option selected>20.00</option>
	        				<option selected>30.00</option>
	      				</select>
	    			</div>
	                <div class="card-footer text-center" id="btSaveAposta">
	                  <button type="submit" class="btn btn-primary btn-icon-split btn-lg mt-3 mb-3" value="Salvar Aposta">
	                    <span class="icon text-white-50">
	                      <i class="fas fa-check"></i>
	                    </span>
	                    <span class="text">Salvar Aposta</span>
	                   </button>
	                </div>
	              </div>
              </form>
              <!-- / Fim do Card-->

            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white footer-seg">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Prática Profissional ADS</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Realmente deseja sair?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">x</span>
          </button>
        </div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
          <a class="btn btn-primary" href="deslogar.jsp">Sair</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>
  
  <!-- Verifica a quantidade de números escolhidos para apostar -->
  <script src="js/check-aposta.js"></script>

</body>

</html>