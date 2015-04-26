<?php
mysql_connect ("localhost", "handipark","handiPARK") or die(mysql_error());
mysql_select_db ("parking") or die(mysql_error);
$result= mysql_query ("SELECT * FROM parking");

while($row = mysql_fetch_assoc($result)){
     $json[] = $row;
}
echo json_encode($json);
/*while ($row=mysql_fetch_assoc($image, MYSQL_ASSOC);
{
//$image = mysql_fetch_assoc ($image);
//print "asdf";
print (json_encode($row));
}*/
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