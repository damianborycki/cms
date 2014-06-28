$.fn.collapseMe = function(opts){
    var $this = this,
        defaults = {
            selector: '#collapsed' // dropdown element
        },
        settings = $.extend(defaults, opts);

    var selector = settings.selector; 
    var lastDocumentWidth = $(document).width();
  
    return this.each(function() {
     
      var children = $this.children(':not(:last-child)');
      var thisWidth = $this.outerWidth();
      var parentWidth = $this.parent().innerWidth();
      
      function grow() {
        
        while(thisWidth < parentWidth) {
          
          console.log("grow");
          
          var collapsed = $(selector).children();
          var count = collapsed.size();
          
          if (count > 0) {
            // move the first dropdown item to end of list
            $(collapsed[0]).insertBefore($this.children(':last-child'));         
            thisWidth = $this.outerWidth();
          }
          else {
            
            $(selector).parent('li').addClass('fade');
            return;
          }
          
        }
      }
      
      function shrink() {
        
        while(thisWidth >= parentWidth) {

          children = $this.children(':not(:last-child)');
          var count = children.size();
          
          if (count<1) {
          	return;
          }
          
          console.log("shrink");
          
          // show the overflow link in case it's been hidden
          $(selector).parent('li').removeClass('fade');

          // move the last item to dropdown
          $(children[count-1]).prependTo(selector);
          
          // recalc width
          thisWidth = $this.outerWidth();

        }
      }

      if (thisWidth>=parentWidth) {
        // the list has wrapped so collapse items into dropdown
        shrink();
      }
      else {
        // the list may have room to expand so remove items from dropdown
        grow();
      }
      
      function refresh() {
          var w = $(document).width();
          
          thisWidth = $this.outerWidth();    
          parentWidth = $this.parent().innerWidth();
        
          if (thisWidth>=parentWidth || (0 > w - lastDocumentWidth)) {
              shrink();
          }
          else if (0 < w - lastDocumentWidth){
              grow();
          }
        
          lastDocumentWidth = w;
      }
	  
	  $(this).hide(function()
	  {
		refresh();
		$(this).show();
	  });
      
      $(window).resize(function() {
            refresh();
      });

    });
   
}

$(document).ready(function() { 
  	$('#topMenu').collapseMe();
});