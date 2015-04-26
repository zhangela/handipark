
<?php

mysql_connect ("localhost", "handipark","handiPARk") or die(mysql_error());
mysql_select_db ("parking") or die(mysql_error);
$iddd = $_GET['idd'];

	$query = mysql_query("DELETE FROM parking WHERE id=$iddd");
	

?>
