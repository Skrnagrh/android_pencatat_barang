<?php
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$dbname = 'risolmart';

$con = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname) or die('Unable to Connect');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	//Getting values
	$kode = $_POST['kode'];
	$nama = $_POST['nama'];
	$harga = $_POST['harga'];
	$stok = $_POST['stok'];

	//Creating sql query
	$sql = "INSERT INTO data_barang VALUES('" . $kode . "','" . $nama . "','" . $harga . "','" . $stok . "')";

	//executing query
	$result = mysqli_query($con, $sql);

	if ($result) {
		//displaying success
		echo "success";
	} else {
		//displaying failure
		echo "failure";
	}
	//-------close database 
	mysqli_close($con);
}
