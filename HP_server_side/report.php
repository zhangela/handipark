

<?php

mysql_connect ("localhost", "handipark","handiPARK") or die(mysql_error());
mysql_select_db ("parking") or die(mysql_error);

		$lngg = $_GET['lng'];
		$latt = $_GET['lat'];
		$comment = $_GET['comment'];
		
//$file = $_FILES['image']['tmp_name'];


	//$image = addslashes(file_get_contents($_FILES['image']['tmp_name']));
	//$image_name = addslashes($_FILES['image']['name']);
	//$image_size = getimagesize($_FILES['image']['tmp_name']);
	
//	if ($image_size==FALSE)
	//	{echo "That's not an image";}
	//else 
//	{
	$query = mysql_query("INSERT INTO img VALUES 			('','','$lngg','$latt','$comment')");
	
	/*if (!$query)
		{
			echo "Problem uploading image..";
		}
		else 
		{
			$lastid = mysql_insert_id();
			echo "Image uploaded.<p />Your image:<p /><img src='show.php?id=$lastid'>";
			
			
		}
		
	//}*/
	


?>

