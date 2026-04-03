const users = [
    { name: "A", role: "admin", salary: 4400 },
    { name: "B", role: "user", salary: 4400 },
    { name: "C", role: "user", salary: 5000 }
];
function groupBySalary(users) {
    const grouped = users.reduce((acc, user) => {
        if (!acc[user.salary]) acc[user.salary] = [];
        acc[user.salary].push(user);
        return acc;
    }, {});

    console.log(grouped);
}

function groupByRole(users) {
    const grouped = users.reduce((acc, user) => {
        if (!acc[user.role]) acc[user.role] = [];
        acc[user.role].push(user);
        return acc;
    }, {});

    console.log(grouped);
}

groupBySalary(users);
groupByRole(users);
