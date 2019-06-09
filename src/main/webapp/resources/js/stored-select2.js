$(document).ready(function() { 
     $('select[name="serviceId"]').change(function() {		         
    	 localStorage.setItem('serviceId', $(this).val());
     });
     
     $('select[name="cityId"]').change(function() { 
    	 localStorage.setItem('cityId', $(this).val()); 
     });
     
     if(localStorage.getItem('serviceId')) { 
         $('select[name="serviceId"]').val(localStorage.getItem('serviceId')).find('option[value="' + localStorage.getItem('serviceId') + '"]').attr('selected', 'selected'); 
     }; 
     
     if(localStorage.getItem('cityId')) { 
         $('select[name="cityId"]').val(localStorage.getItem('cityId')).find('option[value="' + localStorage.getItem('cityId') + '"]').attr('selected', 'selected'); 
     };     
});