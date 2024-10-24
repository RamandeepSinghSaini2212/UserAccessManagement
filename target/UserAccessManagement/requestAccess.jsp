<form action="requestAccess" method="post">
    <label for="software">Software:</label>
    <select name="software" id="software">
        <!-- Populate with software options from database -->
    </select><br>
    <label for="reason">Reason:</label>
    <textarea name="reason" id="reason" required></textarea><br>
    <input type="submit" value="Request Access">
</form>
