<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
<section class="vh-100">
            <div class=" d-flex align-items-center h-100">
              <div class="container h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                   
                  <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <h3 class="d-flex justify-content-center ">Smart Inventory Management System</h3>
                    <div class="card" style="border-radius: 15px;">
                      <div class="card-body py-3 px-5">
                        <h2 class="text-uppercase text-center mb-3">Register</h2>
          
                        <form method="post" action="registerUser" >
          
                          <div class="form-outline mb-2">
                            <input type="text" name="name" class="form-control form-control-lg" />
                            <label class="form-label" for="name">Your Name</label>
                          </div>
          
                          <div class="form-outline mb-2">
                            <input type="email" name="email" class="form-control form-control-lg" />
                            <label class="form-label" for="email">Your Email</label>
                          </div>
          
                          <div class="form-outline mb-2">
                            <input type="password" name="password" class="form-control form-control-lg" />
                            <label class="form-label" for="password">Password</label>
                          </div>
          
                          <div class="form-outline mb-3">
                            <input type="text" name="contact" class="form-control form-control-lg" />
                            <label class="form-label" for="contact">Your Contact No</label>
                          </div>
          
                          
          
                          <div class="d-flex justify-content-center">
                            <button type="submit" class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Sign Up</button>
                          </div>
          
                          <p class="text-center text-muted mt-3 mb-0">Already have an account? <a href="login" class="fw-bold text-body"><u>Login here</u></a></p>
          
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