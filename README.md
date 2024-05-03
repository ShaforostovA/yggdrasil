<h1 style="text-align:center;">Онлайн-сервис для автоматизации формирования отчетов вуза по научно-исследовательской работе</h1>
<div>
  Это мой первый крупный проект, который был реализован с использованием Java.
</div>
<br/>
<div>
  Данный сервис разрабатывался мною в качестве дипломной работы в институте, но нуждается в доработке и код стоит переписать.
</div>
<br/>
<dir>
  <h3>Суть проекта:</h3>
  <dir>
    Автоматизировать процесс формирования отчетности вуза по научно-исследовательской работе.
    <br/>
    <br/>
    Преподаватели и заведующие кафедрой заполняют все необходимые документы. Заведующие кафедрой проверяют правильность заполнения документов преподавателями и если есть проблемы, то отправляют на редактирование.
    <br/>
    <br/>
    Заведующие кафедрой также могут формировать отчеты по своей кафедре. Глава научного отдела может создавать новые шаблоны документов и отчетов, используя конструктор документов и отчетов. В полномочия главы научного отдела также входит возможность редактирования документов, создания новых документов, изменение структуры института внутри системы (изменять кафедры, добавлять/удалять сотрудников и т.п.),
    создание новых шаблонов документов и отчетов, используя конструкторы документов и отчетов.
    <br/>
    <br/>
    Технический администратор может все, что связано с созданием шаблонов документов и отчетов, с редактированием структуры института и сотрудниками. Эта роль нужна для того, чтобы можно было делегировать обязанности по настройки системы техническому специалисту института.
  </dir>
  <br/>
  <br/>
  <dir>
    <h4>В системе присутствовало 4 роли пользователей:</h4>
    <list>
      <li>Преподаватель;</li>
      <li>Заведующий кафедрой;</li>
      <li>Глава научного отдела;</li>
      <li>Технический администратор.</li>
    </list>
  </dir>
</dir>
<br/>
<div>
  <h3>
    Данный сервис состоит из двух частей:
  </h3>
  <list>
    <li>Серверная часть - <a href="https://github.com/ShaforostovA/Yggdrasil.git" target=blacnk>GIT</a></li>
    <li>Клиентская часть - <a href="https://github.com/ShaforostovA/yggdrasil-client.git" target=blacnk>GIT</a></li>
  </list>
</div>
<br/>
<div>
  Для реализации серверной части и общения с клиентской, использовался подход <b>REST API</b>.
</div>
<br/>
<dir>
  <h3>Используемые мной технологии для реализации серверной части:</h3>
  <list>
    <li>Java;</li>
    <li>Java Spring;</li>
    <li>Spring Security;</li>
    <li>Hibernate;</li>
    <li>PostgreSQL.</li>
  </list>
</dir>
<br/>
<dir>
  База данных и backend разворачивались в Docker-контейнерах.
</dir>
<br/>
<dir>
  Для аутентификации использовался подход: <b>JWT</b>.
</dir>
