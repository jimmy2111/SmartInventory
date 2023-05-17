<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>401-Error</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3 mb-5">
        <a class="navbar-brand" href="#">Smart Inventory Management System</a>
        <div class="collapse navbar-collapse justify-content-end" id="navbarText">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item ">
              <a class="nav-link" href="index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="viewallinventory">Inventory</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="viewallproducts">Products</a>
              </li>
            
          </ul>
          <form class="form-inline d-flex" action = "searchproduct" method="post">
            <input class="form-control mr-sm-2 me-2 ms-2" type="text" name="name" placeholder="Enter Product Name" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </nav>
<div class="d-flex flex-column justify-content-center align-items-center">
<h2>An Error Occured!</h2>
<h5>${statusCode} - - - ${statusMessage}</h5>
<a class="btn btn-primary" href="index">Home</a>
</div>
</body>
</html>