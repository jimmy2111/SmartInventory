<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Supplier</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3 mb-5">
		<a class="navbar-brand" href="#">Smart Inventory Management System</a>
		<div class="collapse navbar-collapse justify-content-end"
			id="navbarText">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item "><a class="nav-link" href="index">Home</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="viewallinventory">Inventory</a></li>
				<li class="nav-item"><a class="nav-link" href="viewallproducts">Products</a>
				</li>
				
			</ul>
			<form class="form-inline d-flex" action="searchproduct" method="post">
				<input class="form-control mr-sm-2 me-2 ms-2" type="text"
					name="name" placeholder="Enter Product Name" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>

	<div class="container" style="width: 30%"float:"center">
		<h2>${success}</h2>
		<b><h3>Enter Supplier Details</h3></b><br>
		<form action="savesupplier" method="post">
			<input type="hidden" name="id" value="${supplier1.id}" />
			<div class="form-group">
				<label for="name">Name:</label> <input type="text"
					class="form-control" id="name" name="name"
					placeholder="Enter supplier's name">
			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					class="form-control" id="address" name="address"
					placeholder="Enter Address">
			</div>
			<div class="form-group">
				<label for="contact">Contact:</label> <input type="text"
					class="form-control" id="contact" name="contact"
					placeholder="Enter contact number">
				<p>Contact number must be unique</p>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Save Supplier</button>
			<a href="addSuppliers" class="btn btn-primary"> Add new Supplier</a>
		</form>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>