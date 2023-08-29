<?php

$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$dbname = 'risolmart';

$con = mysqli_connect($dbhost, $dbuser, $dbpass, $dbname) or die('Unable to Connect');

$query = "SELECT * FROM data_barang"; //select table in database
$sql = mysqli_query($con, $query);
$arraydata = array();
while ($data = mysqli_fetch_array($sql)) {
    $arraydata[] = $data;
}

echo json_encode($arraydata);

mysqli_close($con);
