const users=[
    {name:"B",age:20},
     {name:"A",age:20},
      {name:"C",age:18}
];
users.sort((a,b)=>{
    if(a.age!==b.age) return a.age-b.age;
    return a.name.localeCompare(b.name);
});
console.log(users)