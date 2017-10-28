todoApp.controller('dashBoardController', function($scope, $rootScope, $http,
		$q, dashBoardService, fileUpload, fileUploadEndpoint) {
 
	 $scope.totalTime="";
	 $scope.flagSuccess=false;
	 $scope.flagError=false;
	 $scope.saveFile={};

	$scope.submitUser = function(file) {
		
		$scope.saveFile=file
		
		if(file==undefined){
			$scope.flagError=true;
			$scope.message="Please Csv Select File ";
		}
		 
		else{
		
		var uploadUrl = "/assignment-service/uploadfile";
		var fd = new FormData();
		fd.append('file', $scope.saveFile);

		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function(data) {
			$scope.flagError=false;
			$scope.flagSuccess=true;
			$scope.message="Record Saved Successfully in ";
			$scope.totalTime=data;
			fileUpload.getFileData($scope,$rootScope); 
			

		}).error(function() {
			console.log('error');
		});
		}
	}
	
	
	$scope.getGridData=()=>{
		  fileUpload.getFileData($scope,$rootScope);
		  
	}
	 
	 
	
	$scope.clearAll=()=>{
		$scope.totalTime="";
		 $scope.flagSuccess=false;
		 $scope.flagError=false;
		 $scope.saveFile={};
		 angular.element("input[type='file']").val(null);
	}
	

});