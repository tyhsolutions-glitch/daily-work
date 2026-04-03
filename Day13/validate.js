function validate(obj,requiredKeys){
    return requiredKeys.every(key=>key in obj);
}
const data={name:"Pariwesh",age:25};
console.log(validate(data,["name","age","email"]));