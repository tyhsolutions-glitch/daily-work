const arr=[
    {id:1,name:"A"},
        {id:1,name:"A"},
            {id:2,name:"B"}
];
const unique=Array.from(
    new Map(arr.map(item=>[item])).values()
);
console.log(unique);