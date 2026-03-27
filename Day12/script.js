let todos = JSON.parse(localStorage.getItem('todos')) || [];

const input = document.getElementById('todo-input');
const dateInput = document.getElementById('todo-date');
const timeInput = document.getElementById('todo-time');
const errorDiv = document.getElementById('error');
const addBtn = document.getElementById('add-btn');
const sortBtn = document.getElementById('sort-btn');

input.addEventListener('input', validate);
dateInput.addEventListener('change', validateDateTime);
timeInput.addEventListener('change', validateDateTime);
addBtn.addEventListener('click', addTodo);
sortBtn.addEventListener('click', sortTodos);
const today = new Date().toISOString().split('T')[0];
dateInput.setAttribute('min', today);
function validate(event) {
    if (input.value.trim() !== '') {
        errorDiv.style.display = 'none';
    } else {
        errorDiv.style.display = 'block';
    }
}
function validateDateTime() {
    const selectedDate = dateInput.value;
    const selectedTime = timeInput.value;

    if (!selectedDate || !selectedTime) return;

    const selectedDateTime = new Date(`${selectedDate}T${selectedTime}`);
    const now = new Date();

    if (selectedDateTime < now) {
        errorDiv.textContent = 'Cannot select past date/time';
        errorDiv.style.display = 'block';
        return false;
    } else {
        errorDiv.style.display = 'none';
        return true;
    }
}
function saveTodos() {
    localStorage.setItem('todos', JSON.stringify(todos));
}
function addTodo() {
    const text = input.value.trim();
    const date = dateInput.value;
    const time = timeInput.value;
    const priority = document.querySelector('input[name="priority"]:checked').value;
    if (text === '') {
        errorDiv.textContent = 'Field cannot be empty';
        errorDiv.style.display = 'block';
        return;
    }
    if (!validateDateTime()) return;
    todos.push({
        text,
        date,
        time,
        priority,
        completed: false,
        startTime: new Date().getTime(),
        completedTime: null
    });
    input.value = '';
    dateInput.value = '';
    timeInput.value = '';
    errorDiv.style.display = 'none';
    saveTodos();
    renderTodos();
}
function sortTodos() {
    todos.sort((a, b) => a.text.localeCompare(b.text, undefined, { sensitivity: 'base' }));
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
        if (todo.completed) li.classList.add('completed');
        let timeTaken = '';
        if (todo.completed && todo.completedTime) {
            const diff = Math.floor((todo.completedTime - todo.startTime) / 1000);
            const minutes = Math.floor(diff / 60);
            const seconds = diff % 60;
            timeTaken = ` (Time taken: ${minutes}m ${seconds}s)`;
        }
        const priorityClass = todo.priority === 'Urgent' ? 'urgent-text' : 'normal-text';
        li.innerHTML = `
            <span>
                ${todo.text} - ${todo.date} ${todo.time}
                <strong class="${priorityClass}">[${todo.priority}]</strong>
                ${timeTaken}
            </span>
            <div>
                <button onclick="toggleComplete(${index})">✅</button>
                <button onclick="deleteTodo(${index})">❌</button>
            </div>
        `;
        list.appendChild(li);
    });
}
renderTodos();