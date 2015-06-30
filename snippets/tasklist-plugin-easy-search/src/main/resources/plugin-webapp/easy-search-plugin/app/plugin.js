define(['angular'], function(angular) {

  var expressionsRegex = /^[\s]*(\#|\$)\{/;
  var sanitizeProperty = function(type, operator, value) {
    var out = type;
    if(['Like', 'Before', 'After'].indexOf(operator) !== -1) {
      out += operator;
    }
    if(expressionsRegex.test(value) &&
       ['assignee', 'owner', 'candidateGroup', 'candidateUser', 'involvedUser'].indexOf(type) !== -1) {
      out += 'Expression';
    }
    return out;
  };
  
  var sanitizeValue = function(value, operator) {
    if(operator === 'Like' || operator === 'like') {
      return '%'+value+'%';
    }
    return value;
  };  

  var SearchController = function($scope, $http, Uri, $location, search) {


    $scope.searchQuery = "";
	$scope.executeSearch = function() {
	
		
		var searchData = $scope.tasklistData.newChild($scope);
		//var query = "[{'type':'processVariables','operator':'like','value':'SEARCH','name':'x'}]";
		
		
		//query[sanitizeProperty("processVariables", "Like", "SEARCH")] = sanitizeValue("x", "Like");
		
		var query = {"processVariables":[{"name":"SEARCH","operator":"like","value":"%"+$scope.searchQuery+"%"}]};
		
		query = searchData.set('searchQuery', query);

	  //search.updateSilently( "[{'type':'processVariables','operator':'like','value':'SEARCH','name':'x'}]" );	  
	  console.log( $scope.tasklistData );


	}

	
/*
        $scope.createSearch = function(type){
          var search = createSearchObj(type);
          if(!parseSearch(search, $scope.inputQuery)) {
            search.value = $scope.inputQuery;
            search.operators = getOperators(getType(parseValue(search.value)));
          }
          $scope.searches.push(search);
          if(isValid(search)) {
            updateQuery();
          }

          // need to use timeout, because jQuery initiates an apply cycle
          // while the current apply cycle is still in progress
          $timeout(function(){angular.element('.search-container > input').blur();});
          $scope.dropdownOpen = false;
          $scope.inputQuery = '';
        };
        
        function updateQuery() {
          var outArray = [];
          angular.forEach($scope.searches, function(search) {
            if(isValid(search)) {
              outArray.unshift({
                name: search.name,
                operator: search.operator,
                value: parseValue(search.value),
                type: search.type
              });
            }
          });

          search.updateSilently({
            query: JSON.stringify(outArray)
          });
*/
  };

  

  var Configuration = ['ViewsProvider', function(ViewsProvider) {

    ViewsProvider.registerDefaultView('tasklist.list', {
      id: 'easy-search-plugin',
      label: 'Search',
      url: 'plugin://easy-search-plugin/static/app/search.html',
      controller: SearchController,

      // less priority as form-detail plugin (https://github.com/camunda/camunda-tasklist-ui/blob/master/client/scripts/task/plugins/detail/cam-tasklist-task-detail-form-plugin.js) 
      priority: 800
    });
  }];

 
  var ngModule = angular.module('tasklist.plugin.easy-search-plugin', []);

  ngModule.config(Configuration);

  return ngModule;
});
