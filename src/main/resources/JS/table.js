const fillTableHandle = async () => {
  const table = document.getElementById("usersTable");
  const users = await getUsersAction();

  let usersTableHTML = "";
  for (const user in users) {
    usersTableHTML += `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${user.role.map((role) => role.rolename).join(" ")}</td>
                <td>
                    <button class="btn btn-info btn-sm text-white"
                            data-toggle="modal"
                            data-target="#editModal"
                            data-user-id="${user.id}">
                        Edit</button>
                </td>
                <td>
                    <button class="btn btn-danger btn-sm btn-delete"
                            data-toggle="modal"
                            data-target="#deleteModal"
                            data-user-id="${user.id}">                     
                        Delete</button>
                </td>
            </tr>`;
  }

  table.innerHTML = usersTableHTML;
};
