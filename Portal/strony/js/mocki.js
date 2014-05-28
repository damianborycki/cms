function getListOfArticles(len){
	var arr = [];
  for (var i=0;i<len;i++)
  {
  arr.push({
     title: "Tytul",
     description: "Opis",
     publication_date: "2012-04-23 18:25:43",
     views:10,
     image: 1,
     tag: [1,5,9],
     rank: 1,  
     commntCount: 5
     });
  }
  return arr;
}

function getListOfCommentsMadeByUser(userId, len){

   var arr = [{
   login: 'zbyszek88',
   commentId : 12345,
   userId : 12345,
   content : "Sample content Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.",
   date : "25.04.14",
   time: "14.05",
   nr_of_replies : 0
   },
   {
   login: 'zbyszek88',
   commentId : 12346,
   userId : 12345,
   content : "Sample content234234",
   date : "25.04.14",
   time: "14.10",
   nr_of_replies : 4
   },
   {
   login: 'zbyszek88',
   commentId : 12347,
   userId : 12345,
   content : "Sample content23423455",
   date : "25.04.14",
   time: "14.15",
   nr_of_replies : 4
   },
   {
   login: 'zbyszek88',
   commentId : 12348,
   userId : 12345,
   content : "Sample content2342345566",
   date : "25.04.14",
   time: "14.20",
   nr_of_replies : 4
   }
   ];
   
   	return arr;
}

function GetUserById(id)
{
	return {'login': 'zbyszek88',
	'email': 'z.nowak@o2.pl',
	'firstname': 'Zbigniew',
	'lastame': 'Nowak',
	'gender': 'M',
	'city': 'Kraków',
	'avatar': 'path/to/avatar.jpg',
	'info': 'Hej, jestem Zbyszek. Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
	'age': '25'};
}