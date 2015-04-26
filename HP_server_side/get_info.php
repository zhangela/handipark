<?php
mysql_connect ("localhost", "handipark","handiPARK") or die(mysql_error());
mysql_select_db ("parking") or die(mysql_error);
$iddd= $_GET['iddd'];
$image= mysql_query ("SELECT * FROM parking WHERE id=$iddd");
$image = mysql_fetch_assoc ($image);
print (json_encode($image));
/*
$name = $image['name'];
$long = $image['lng'];
$lat = $image['lat'];
print (json_encode($name));
print (json_encode($long));
print (json_encode($lat));
//echo $name;
//echo $long;
//echo $lat;
*/

?>