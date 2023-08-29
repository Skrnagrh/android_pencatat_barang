<?php
$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$dbname = 'risolmart';

$con = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname) or die('Unable to Connect');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	//Getting values
	$kode = $_POST['kode'];

	//Creating sql query
	$sql = "DELETE FROM data_barang WHERE kode='" . $kode . "'";

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
