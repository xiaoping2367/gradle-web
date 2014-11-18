<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style>

 .custom-colorize {
    font-size: 20px;
    position: relative;
    width: 75px;
    height: 75px;
  }
  .custom-colorize-changer {
    font-size: 10px;
    position: absolute;
    right: 0;
    bottom: 0;
  }
</style>



<script>

$(function() {
 
	$.widget( "custom.colorize", {
	      // default options
	      options: {
	        red: 255,
	        green: 0,
	        blue: 0,
	 
	        // callbacks
	        change: null,
	        random: null
	      },
	 
	      // the constructor
	      _create: function() {
	    	  debugger
	        this.element
	          // add a class for theming
	          .addClass( "custom-colorize" )
	          // prevent double click to select text
	          .disableSelection();
	 
	    	this.appender = $("<button>");
	        this.changer = $( "<button>", {
	          text: "change",
	          "class": "custom-colorize-changer"
	        })
	        .appendTo( this.element )
	        .button();
	 
	        // bind click events on the changer button to the random method
	        this._on( this.changer, {
	          // _on won't call random when widget is disabled
	          click: "random"
	        });
	        this._refresh();
	      },
	 
	      // called when created, and later when changing options
	      _refresh: function() {
	        this.element.css( "background-color", "rgb(" +
	          this.options.red +"," +
	          this.options.green + "," +
	          this.options.blue + ")"
	        );
	 
	        // trigger a callback/event
	        this._trigger( "change" );
	      },
	 
	      // a public method to change the color to a random value
	      // can be called directly via .colorize( "random" )
	      random: function( event ) {
	        var colors = {
	          red: Math.floor( Math.random() * 256 ),
	          green: Math.floor( Math.random() * 256 ),
	          blue: Math.floor( Math.random() * 256 )
	        };
	 
	        // trigger an event, check if it's canceled
	        if ( this._trigger( "random", event, colors ) !== false ) {
	          this.option( colors );
	        }
	      },
	 
	      // events bound via _on are removed automatically
	      // revert other modifications here
	      _destroy: function() {
	        // remove generated elements
	        this.changer.remove();
	 
	        this.element
	          .removeClass( "custom-colorize" )
	          .enableSelection()
	          .css( "background-color", "transparent" );
	      },
	 
	      // _setOptions is called with a hash of all options that are changing
	      // always refresh when changing options
	      _setOptions: function() {
	        // _super and _superApply handle keeping the right this-context
	        this._superApply( arguments );
	        this._refresh();
	      },
	 
	      // _setOption is called for each individual option that is changing
	      _setOption: function( key, value ) {
	        // prevent invalid color values
	        if ( /red|green|blue/.test(key) && (value < 0 || value > 255) ) {
	          return;
	        }
	        this._super( key, value );
	      }
	    });
	 
	    // initialize with default options
	    $( "#my-widget1" ).colorize();
	 
	    // initialize with two customized options
	    $( "#my-widget2" ).colorize({
	      red: 60,
	      blue: 60
	    });
	 
	    // initialize with custom green value
	    // and a random callback to allow only colors with enough green
	    $( "#my-widget3" ).colorize( {
	      green: 128,
	      random: function( event, ui ) {
	        return ui.green > 128;
	      }
	    });
	 
	    // click to toggle enabled/disabled
	    $( "#disable" ).click(function() {
	      // use the custom selector created for each widget to find all instances
	      // all instances are toggled together, so we can check the state from the first
	      if ( $( ":custom-colorize" ).colorize( "option", "disabled" ) ) {
	        $( ":custom-colorize" ).colorize( "enable" );
	      } else {
	        $( ":custom-colorize" ).colorize( "disable" );
	      }
	    });
	 
	    // click to set options after initialization
	    $( "#green" ).click( function() {
	      $( ":custom-colorize" ).colorize( "option", {
	        red: 64,
	        green: 250,
	        blue: 8
	      });
	    });
	
	

}); 




$(document).ready(function() {
    //$("input.languageWidget").button({text:"dddddddddddddddddd"}).click(function() {
    //	alert("hello");
    //});
});

</script>
</head>
<body>


  <input  type="text" value="aaaaaaaaaaa" class="languageWidget">
  

<div>
  <div id="my-widget1">color me</div>
  <div id="my-widget2">color me</div>
  <div id="my-widget3">color me</div>
  <button id="disable">Toggle disabled option</button>
  <button id="green">Go green</button>
</div>




</body>
</html>