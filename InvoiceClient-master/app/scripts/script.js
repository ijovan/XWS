function proveriParSU(prefix, br, leng)
{
	var id = '#'+prefix+br;
	var iid = '#'+prefix+'i'+br;
	var idp = prefix+br;
	
	var a=$(iid).val();
	
	if (a==null || a=="")
	{
		$(id).css('visibility', 'visible');
		document.getElementById(idp).innerHTML = 'Obavezno polje';
	}
	else
	{
		if (a.length>leng)
		{
			document.getElementById(idp).innerHTML = 'Predugačak unos (do '+leng+' karaktera)';
			$(id).css('visibility', 'visible');
		}
		else
			$(id).css('visibility', 'hidden');
	}
	
	/*
	if (a==null || a=="")
		$(id).css('visibility', 'visible');
	else
		$(id).css('visibility', 'hidden');
	*/
}


function proveriParSUDouble(prefix, br, cifara)
{
	var id = '#'+prefix+br;
	var iid = '#'+prefix+'i'+br;
	var idp = prefix+br;
	
	var a=$(iid).val();
	
	//var pattern = /^\d{0,15}(\.\d{0,2}){0,1}$/;
	var pattern = new RegExp("^\\d{0,"+cifara+"}(\\.\\d{0,2}){0,1}$"); 
	
	if (a==null || a=="")
	{
		$(id).css('visibility', 'visible');
		document.getElementById(idp).innerHTML = 'Obavezno polje';
	}
	else
	{
		if (!pattern.test(a))
		{
			document.getElementById(idp).innerHTML = 'Unesite decimalni formal (do '+ cifara+' cifara, 2 decimale)';
			$(id).css('visibility', 'visible');
		}
		else
			$(id).css('visibility', 'hidden');
	}
}

function proveriParSUInt(prefix,br, cifara)
{
	var id = '#'+prefix+br;
	var iid = '#'+prefix+'i'+br;
	var idp = prefix+br;
	
	var a=$(iid).val();
	
	//var pattern = /^\d+$/;
	var pattern = new RegExp("^\\d{0,"+cifara+"}$"); 
	
	
	if (a==null || a=="")
	{
		$(id).css('visibility', 'visible');
		document.getElementById(idp).innerHTML = 'Obavezno polje';
	}
	else
	{
		if (!pattern.test(a))
		{
			document.getElementById(idp).innerHTML = 'Unesite celobrojnu vrednost (do '+cifara+' cifara)';
			$(id).css('visibility', 'visible');
		}
		else
			$(id).css('visibility', 'hidden');
	}
}

function proveriParSUDate(prefix,br)
{
	var id = '#'+prefix+br;
	var iid = '#'+prefix+'i'+br;
	var idp = prefix+br;
	
	var a=$(iid).val();
	
	var pattern =/^([0-9]{4})\-([0-9]{2})\-([0-9]{2})$/;
	
	if (a==null || a=="")
	{
		$(id).css('visibility', 'visible');
		document.getElementById(idp).innerHTML = 'Obavezno polje';
	}
	else
	{
		if (!pattern.test(a))
		{
			document.getElementById(idp).innerHTML = 'Unesite yyyy-mm-dd format';
			$(id).css('visibility', 'visible');
		}
		else
			$(id).css('visibility', 'hidden');
	}
}
