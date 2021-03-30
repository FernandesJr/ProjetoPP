<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%if(request.getAttribute("msg") != null){%>
	<%= request.getAttribute("msg") %>
<%}%>



<!DOCTYPE html>
<html lang="pt-br">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Jogo Loteria - Login</title>

  <!-- Custom fonts for this template-->
  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="css/sb-admin-2.min.css" rel="stylesheet">
  <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">

  <!--CSS adicional-->
  <link href="css/style.css" rel="stylesheet" >

</head>

<body class="bg-gradient-primary">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">
      <div class="col-xl-10 col-lg-12 col-md-9">
        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <h1 class="tit-pri text-center">Loteria</h1>
            <div class="row">
              
              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <form class="user" action="ControllerLogin" method="post">
                    <div class="form-group">
                      <input name="email" type="email" class="form-control form-control-user" id="exampleInputEmail" aria-describedby="emailHelp" placeholder="Email" required>
                    </div>
                    <div class="form-group">
                      <input name="senha" type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Senha" required>
                    </div>
                    <button class="btn btn-primary btn-user btn-block">
                      Login
                    </button>
                    <hr>
                    <a href="register.html" class="btn btn-google btn-user btn-block">
                      Cadastrar
                    </a>
                  </form>
                  <hr>                 
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <footer class="footer-pri">
    <p class="text-center"><a href="https://github.com/fernandesJr" target="_blank">FernandesJúnior</a> &copy; <a href="#" target="">DiogoBarros</a> &copy; <a href="#" target="">MarcílioGomes</a> &copy;</p>
  </footer>
  <!-- Bootstrap core JavaScript-->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>
</body>
</html>