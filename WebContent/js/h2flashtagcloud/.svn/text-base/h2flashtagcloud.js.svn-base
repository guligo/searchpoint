var h2flash = new Class({
		options:	{
						tagCloudContainer: null,
						tagCloudSpeed: null,
						tagCloudWidth: null,
						tagCloudHeight: null,
						hoverColor: null,
						clickHandle: null,
						autoStart: false,
						isFlashShown: false,
						finished: null
						
		},
					
		initialize:	function(options){
			this.setOptions(options);
			if(options['tagCloudContainer']) this.tagCloudContainer = options['tagCloudContainer'];
			if(options['tagCloudSpeed']) this.tagCloudSpeed = options['tagCloudSpeed'];
			if(options['tagCloudWidth']) this.tagCloudWidth = options['tagCloudWidth'];
			if(options['tagCloudHeight']) this.tagCloudHeight = options['tagCloudHeight'];
			if(options['tagCloudHeight']) this.tagCloudHeight = options['tagCloudHeight'];			
			if(options['clickHandle']) this.clickHandle = options['clickHandle'];
			if(options['autoStart']) this.autoStart = options['autoStart'];
			
			if(this.autoStart == true){
				this.autorun();
			}
			
			if(this.clickHandle != null){
				$(this.clickHandle).addEvent('click',this.run.bindWithEvent(this));
			}
			
			this.fireEvent('complete');
		},
		
		run: function(e){
			e= new Event(e).stop();	
			var rnd = Math.pow(Math.random(),Math.random()) * 100;
			rnd = rnd.toString().split('.');
			rnd = rnd[1];
			var fileName = "transform_cloud_data"+rnd+".xml";
			
			
			//new Request({ url:'deleteCloud.php?id='+rnd }).send();
			
			var cont 	= "#" + this.tagCloudContainer + " a";
			var x 		= 0;	
			
	
			$$(cont).each(function(d){
		
					var content 	= d.get('html');
					var font		= d.getStyle('font-family');
					var color 		= d.getStyle('color').split('#');
					color 			= "0x" + color[1];
					var href 		= escape(d.get('href'));		
					var size 		= d.getStyle('font-size');	
											
					if(x == 0){	
						new Request({ url:'transformCloud.php?content='+content+'&font='+font+'&fs='+size+'&color='+color+'&first=true'+'&href='+href+'&fileName='+rnd, method:'get' }).send();
					}else{
						new Request({ url:'transformCloud.php?content='+content+'&font='+font+'&fs='+size+'&color='+color+'&href='+href+'&fileName='+rnd, method:'get' }).send();
					}
					x++;
		  	 })
		
			var p = new Request({ url:'closeTags.php?fileName='+rnd }).send();
			
			  var obj = new Swiff('text_and_image_cloud.swf', {
				id: 'injected',
				width: this.tagCloudWidth,
				height: this.tagCloudHeight,
				container: this.tagCloudContainer,				
				params: {wmode: 'transparent' },
				vars: {	cloud_data:fileName, tspeed: this.tagCloudSpeed}});
		  		
			
			
	  },
	  
	  autorun: function(){
		  new Request({ url:'deleteCloud.php' }).send();
			var cont = "#" + this.tagCloudContainer + " a";
			var x = 0;
			var rnd = Math.pow(Math.random(),Math.random()) * 100;
			rnd = rnd.toString().split('.');
			rnd = rnd[1];
			var fileName = "transform_cloud_data"+rnd+".xml";
			
			
			$$(cont).each(function(e){
		
					var content 	= e.get('html');
					var font		= e.getStyle('font-family');
					var color 		= e.getStyle('color').split('#');
					color 			= "0x" + color[1];
					var href 		= escape(e.get('href'));		
					var size 		= e.getStyle('font-size');
					
					if(x == 0){	
						new Request({ url:'transformCloud.php?content='+content+'&font='+font+'&fs='+size+'&color='+color+'&first=true'+'&href='+href+'&hoverColor='+this.hoverColor, method:'get' }).send();
					}else{
						new Request({ url:'transformCloud.php?content='+content+'&font='+font+'&fs='+size+'&color='+color+'&href='+href+'&hoverColor='+this.hoverColor, method:'get' }).send();
					}
					x++;
		  	 })
		
			new Request({ url:'closeTags.php'}).send();
			
			var obj = new Swiff('text_and_image_cloud.swf', {
			id: 'injected',
			width: this.tagCloudWidth,
			height: this.tagCloudHeight,
			container: this.tagCloudContainer,
			params: {wmode: 'transparent'},
			vars: {	cloud_data:fileName,tspeed: this.tagCloudSpeed }});
	  }
		  
})
		
h2flash.implement(new Options);
h2flash.implement(new Events);		
		
		