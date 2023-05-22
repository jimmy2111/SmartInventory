<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Products</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
          <form class="form-inline d-flex"action = "searchproduct" method="post">
            <input class="form-control mr-sm-2 me-2 ms-2" type="text" name="name" placeholder="Enter Product Name" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
          </form>
        </div>
      </nav>

<div class="container" style="width:30%; float : center;">
 <h3>${success}</h3>
	<form action="saveproduct" method="post">
	    <h3>Enter Product Details:</h3>
	    <div class="form-group" id="product-list">
	        <div class="product-item">
	            <div class="form-group">
	                <label for="productName">Product Name:</label>
	                <input type="text" class="form-control" id="productName" name="name" placeholder="Enter Product Name">
	            </div>
	            <div class="form-group">
	                <label for="productPrice">Product Price:</label>
	                <input type="text" class="form-control" id="productPrice" name="unitPrice" placeholder="Enter Product Price">
	            </div>
	            <div class="form-group">
	                <label for="productDescription">Product Description:</label>
	                <input type="text" class="form-control" id="productDescription" name="description" placeholder="Enter Product Description">
	            </div>
	            <div class="form-group">
	                <label for="productQuantity">Product Quantity:</label>
	                <input type="number" class="form-control" id="productQuantity" name="quantity"placeholder="Enter Product Quantity">
	            </div>
	            <div class="form-group">
	                <label for="thresholdQuantity">Product Threshold:</label>
	                <input type="number" class="form-control" id="productThreshold" name="thresholdQuantity" placeholder="Enter Threshold Quantity" >
	            </div>
	             <div class="form-group">
	                <label for="productSupplier">Product Supplier:</label>
	                <select class="form-control" id="productSupplier" name="id">
	                <option value="">Select Supplier</option>
	                <c:forEach items="${suppliers}" var="s">
	                <option value="${s.id}">${s.name}</option>
	                </c:forEach>
	                </select>
	            </div>
	        </div>
	    </div><br>
	    <button type="submit" class="btn btn-primary">Save</button>
	    <a href="addProducts" class="btn btn-primary"> Add New Product</a>
	</form>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>