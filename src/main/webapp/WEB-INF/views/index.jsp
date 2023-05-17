<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<!-- Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!-- Bootstrap ICONS --> 
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
    <style>
        .carousel-item img {
            max-height: 300px;
        }
        
        a i {
        	font-size: 50px;
        }
        
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-3 mb-5">
        <a class="navbar-brand" href="#">Smart Inventory Management System</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
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
            <input class="form-control mr-sm-2 me-2 ms-2" type="text" name = "name" placeholder="Enter Product Name" aria-label="Search">
            <input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="Search">Search
          </form>
        </div>
      </nav>

      <div class="container mb-5" >
      <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
          <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="https://soulpageit.com/wp-content/uploads/2020/04/inventory-management-dashboard-1-768x299.jpg" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="https://th.bing.com/th/id/OIP.ktMrpYltxqGvBlN52MbXMQHaDS?w=308&h=155&c=7&r=0&o=5&dpr=1.3&pid=1.7" class="d-block w-100" alt="...">
          </div>
          <div class="carousel-item">
            <img src="https://th.bing.com/th/id/R.7c0e23fdb505ff200d76454bfbde7f4f?rik=p%2fTqJYGYMTFPUQ&riu=http%3a%2f%2fmedia.noria.com%2fsites%2fUploads%2f2019%2f10%2f18%2f96481d1b-dfcb-4b15-a210-17eb397a25e8_ArticleImages_RP31422_1234x694_10182019_extra_large.jpeg&ehk=x0I3t1poyWFY5XSZc1DSshJAcrvxMcmGX%2btMQgEBYzI%3d&risl=&pid=ImgRaw&r=0" class="d-block w-100" alt="...">
          </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
    <div class="row flex-nowrap w-100 text-center m-0">
        <div class="col col-md-4">
        
		<a href="addProducts" class="btn btn-primary d-flex flex-column mx-5"><i class="bi bi-plus-circle-fill"></i><span>Add Products</span></a><br>
		<a href="viewallproducts" class="btn btn-primary mt-4 d-flex flex-column mx-5"><i class="bi bi-eye-fill"></i><span>View Products</span></a><br>
        </div>
        <div class="col col-md-4">
        	<a href="addSuppliers" class="btn btn-primary d-flex flex-column mx-5"><i class="bi bi-person-fill-add"></i><span>Add Supplier</span></a><br>
            <a href="viewsuppliers" class="btn btn-primary mt-4 d-flex flex-column mx-5"><i class="bi bi-person-vcard-fill"></i><span>View Suppliers</span></a>
        </div>
        <div class="col col-md-4">
        <a href="order" class="btn btn-primary d-flex flex-column mx-5"><i class="bi bi-bag-plus-fill"></i><span>Order Now</span></a><br>
        <a href="viewallinventory" class="btn btn-primary mt-4 d-flex flex-column mx-5"><i class="bi bi-cart-plus-fill"></i><span>View Inventory</span></a>
        </div>
		
    </div>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>