
<?php

mysql_connect ("localhost", "handipark","handiPARK") or die(mysql_error());
mysql_select_db ("parking") or die(mysql_error);

		$lngg = $_GET['lng'];
		$latt = $_GET['lat'];
		
		
	$query = mysql_query("INSERT INTO parking VALUES 			('','kjbk','$lngg','$latt')");
	

?>
