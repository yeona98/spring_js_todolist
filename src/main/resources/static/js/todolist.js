const titleInput = document.getElementById("title");
const contentInput = document.getElementById("content");
const authorInput = document.getElementById("author");
const secretInput = document.getElementById("secret");

const submitButton = document.getElementById("todo-submit");

const ul = document.getElementById("todo-list");

async function findAllTodoList() {
    const response = await fetch("http://localhost:8080/todolist");
    const todolist = await response.json();
    //console.log(todolist);

    for (const todo of await todolist) {
        const {id, title, content, author} = todo;

        const li = document.createElement("li");
        const btnUpdate = document.createElement("button");
        btnUpdate.id = id;
        const btnUpdateText = document.createTextNode("수정하기");
        btnUpdate.appendChild(btnUpdateText);
        const btnDelete = document.createElement("button");
        btnDelete.id = id;
        const btnDeleteText = document.createTextNode("삭제하기");
        btnDelete.appendChild(btnDeleteText);

        li.innerText = `id:${id}, title:${title}, content: ${content}, author:${author}`;
        li.id = id;
        ul.append(li);
        ul.append(btnUpdate);
        ul.append(btnDelete);

        btnUpdate.addEventListener('click', async e => {
            // console.log(e.target);
            // console.log(e.target.id);

            const requestData = {
                id: e.target.id,
                title: titleInput.value,
                content: contentInput.value,
                author: authorInput.value,
                secret: secretInput.value
            };
            console.log(requestData);

            await fetch("http://localhost:8080/todolist/" + id,
                {
                    method: "PUT",
                    body: JSON.stringify(requestData),
                    headers: {
                        "Content-Type": "application/json"
                    }
                });


            titleInput.value = "";
            contentInput.value = "";
            authorInput.value = "";
            secretInput.value = "";

            await removeAllChild(ul);
            await findAllTodoList();

        });
        btnDelete.addEventListener('click', async e => {
            const requestData = {
                id: e.target.id
            };

            await fetch("http://localhost:8080/todolist/" + id,
                {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
gi
            await removeAllChild(ul);
            await findAllTodoList();
        });
    }
}

findAllTodoList();

submitButton.addEventListener('click', async e => {
    e.preventDefault();
    const requestData = {
        title: titleInput.value,
        content: contentInput.value,
        author: authorInput.value,
        secret: secretInput.value
    };

    console.log(requestData);

    await fetch("http://localhost:8080/todolist",
        {
            method: "POST",
            body: JSON.stringify(requestData),
            headers: {
                "Content-Type": "application/json"
            }
        });


    titleInput.value = "";
    contentInput.value = "";
    authorInput.value = "";
    secretInput.value = "";

    await removeAllChild(ul);
    await findAllTodoList();
});


async function removeAllChild(ul) {
    while (ul.hasChildNodes()) {
        ul.removeChild(ul.firstChild);
    }
}