let todos = JSON.parse(localStorage.getItem('todos')) || [];

function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos));
}
function addTodo() {
    const input = document.getElementById('todo-input');
    const timeInput = document.getElementById('todo-time');
    const text = input.value.trim();
    const time = timeInput.value;
    if (text === '') return;
    todos.push({
        text,
        time,
        completed: false,
        startTime: new Date().getTime(),
        completedTime: null
    });
    input.value = '';
    timeInput.value = '';
    saveTodos();
    renderTodos();
}
function deleteTodo(index) {
    todos.splice(index, 1);
    saveTodos();
    renderTodos();
}
function toggleComplete(index) {
    const todo = todos[index];
    todo.completed = !todo.completed;
    if (todo.completed) {
        todo.completedTime = new Date().getTime();
    } else {
        todo.completedTime = null;
    }
    saveTodos();
    renderTodos();
}
function renderTodos() {
    const list = document.getElementById('todo-list');
    list.innerHTML = '';
    todos.forEach((todo, index) => {
        const li = document.createElement('li');
        if (todo.completed) {
            li.classList.add('completed');
        }
        let timeTaken = '';
        if (todo.completed && todo.completedTime) {
            const diff = Math.floor((todo.completedTime - todo.startTime) / 1000);
            const minutes = Math.floor(diff / 60);
            const seconds = diff % 60;
            timeTaken = ` (Time taken: ${minutes}m ${seconds}s)`;
        }
        li.innerHTML = `
            <span>${todo.text} - ${todo.time}${timeTaken}</span>
            <div>
                <button class="tick" onclick="toggleComplete(${index})">✔</button>
                <button class="delete" onclick="deleteTodo(${index})">✖</button>
            </div>
        `;
        list.appendChild(li);
    });
}
renderTodos();

