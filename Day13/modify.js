const user={name:"John", age:20};
let jsonStr = JSON.stringify(user);
//Covert to object,modify safely
let temp=JSON.parse(jsonStr);
temp.isAdult=temp.age>=18;
let finalObj=temp;
console.log(finalObj);
//{name:"John",age:20,isAdult:true}