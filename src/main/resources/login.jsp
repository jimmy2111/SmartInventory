<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
<section class="vh-100">
            <div class="d-flex align-items-center h-100">
              <div class="container h-100">
                
                <div class="row d-flex justify-content-center align-items-center h-100">
                  <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <h3 class="d-flex justify-content-center mb-4">Smart Inventory Management System</h3>
                    <div class="card" style="border-radius: 15px;">
                      <div class="card-body py-3 px-5">
                        <h2 class="text-uppercase text-center mb-5">Login</h2>
          				<h6>${error}</h6>
                        <form method="post" action="validateUser">
                   
                          <div class="form-outline mb-2">
                            <label class="form-label" for="">Email</label>
                            <input type="email" name="email" class="form-control form-control-lg" />
                            
                          </div>
          
                          <div class="form-outline mb-4">
                            <label class="form-label" for="password">Password</label>
                            <input type="password" name="password" class="form-control form-control-lg" />
                            
                          </div>
                   
                          <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Login</button>
                          </div>
          
                          <p class="text-center text-muted mt-3 mb-0">New User? <a href="register" class="fw-bold text-body"><u>Register here</u></a></p>
                          <p class="text-center text-muted mt-3 mb-0"><a href="updatepassword.html" class="fw-bold text-body"><u>Forgot Password</u></a></p>
          
                        </form>
          
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>