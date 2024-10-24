<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Requests</title>
</head>
<body>
    <h2>Pending Access Requests</h2>

    <table border="1">
        <thead>
            <tr>
                <th>User</th>
                <th>Software</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the pendingRequests result set -->
            <c:forEach var="request" items="${pendingRequests}">
                <tr>
                    <td>${request.username}</td>
                    <td>${request.name}</td>
                    <td>${request.reason}</td>
                    <td>${request.status}</td>
                    <td>
                        <form action="pendingRequests" method="post" style="display:inline;">
                            <input type="hidden" name="requestId" value="${request.id}">
                            <button type="submit" name="action" value="approve">Approve</button>
                        </form>
                        <form action="pendingRequests" method="post" style="display:inline;">
                            <input type="hidden" name="requestId" value="${request.id}">
                            <button type="submit" name="action" value="reject">Reject</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p><a href="managerDashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
