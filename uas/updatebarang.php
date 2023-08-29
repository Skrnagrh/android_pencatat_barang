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
	$sql = "UPDATE data_barang set nama='" . $nama . "',harga='" . $harga . "',stok='" . $stok . "' WHERE kode='" . $kode . "'";

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
