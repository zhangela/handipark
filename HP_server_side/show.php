<?php

mysql_connect ("localhost", "handipark","handiPARK") or die(mysql_error());
mysql_select_db ("parking") or die(mysql_error);

//$id = addslashes($_REQUEST['id']);
 $iddd = $_GET['idd']; 
$image = mysql_query ("SELECT * FROM img WHERE id=$iddd");
$image = mysql_fetch_assoc ($image);
$image = $image['img'];

header ("Content-type: image/jpeg");
echo $image;

?>
