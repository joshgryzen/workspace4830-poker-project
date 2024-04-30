<html>
<head>
<style>
header {
	background-color: black;
	color: white;
	text-align: center;
	padding: 5px;
}

nav {
	line-height: 30px;
	background-color: #eeeeee;
	height: 300px;
	width: 100px;
	float: left;
	padding: 5px;
}

section {
	float: left;
	width: calc(100% - 120px);
	/* Subtracting the width of the sidebar (nav) */
	padding: 5px;
}

footer {
	background-color: black;
	color: white;
	clear: both;
	text-align: center;
	padding: 5px;
}

</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.css">
<script>
$(document).ready(function() {
    // Initialize DataTable
    var table = $('#pokerTable').DataTable({
    	
		    	columnDefs: [
		    		{
		    	        targets: '_all', // Target all columns
		    	        className: 'dt-center' // Apply 'dt-center' class to center align data
		    	    },
		    		{
		            targets: -1, // Target the last column (Quantity)
		            render: function(data, type, row, meta) {
		                // Render increment and decrement buttons and delete button
		                return '<span>' + data + '</span>' + '%'
		                    // '<button class="delete" data-row="' + meta.row + '">Delete</button>';
		                    
		            }
		        }]
        
            });
    
    // Make AJAX call to fetch data from servlet
    $.ajax({
        url: 'HomePageServlet', // Specify the URL of your servlet
        method: 'POST', // Use POST method to send data
        success: function(response) {
            // Iterate over the retrieved cards and add them to the table
            response.forEach(function(game) {
                table.row.add([
                	game.hand,
                	game.flop,
                	game.turn,
                	game.players,
                	game.stats
                ]).draw();
            });
        },
        error: function(xhr, status, error) {
            // Handle errors
            console.error(error);
        }
    });
    
    /*
    $('#pokerTable tbody').on('click', 'button.delete', function() {
        var rowIdx = $(this).data('row');
        var rowData = table.row(rowIdx).data();
        var cardName = rowData[3]; // Assuming the card name is in the 4th column
        deleteCard(cardName);
        table.row($(this).parents('tr')).remove().draw(false); // Remove the row from the DataTable
    });

    // Function to delete the card
    function deleteCard(cardName) {
        $.ajax({
            url: 'DeleteCardServlet', // URL of your servlet for deleting a card
            method: 'POST',
            data: {
                cardName: cardName
            },
            success: function(response) {
                // Handle success response if needed
            },
            error: function(xhr, status, error) {
                console.error(error);
            }
        });
    }
    */
    
});
</script>
</head>
<body>
	<header>
		<h1>Home Page</h1>
	</header>
	<nav>

		<a href="/poker-project/home.jsp">Home</a> <br> 
		<a href="/poker-project/cardgame.html">Visual Insert</a> <br>
		<a href="/poker-project/insert.html">Text Insert</a> <br>
		<a href="/poker-project/loginScreen.html">Log Out</a> <br>
		<a href="/poker-project/home.jsp">Home</a> <br> <a
			href="/poker-project/cardgame.html">Insert Cards</a> <br>
	</nav>
	<section>
		<table id="pokerTable" class="display">
			<thead>
				<tr>
					<th>Hand</th>
					<th>Flop</th>
					<th>Turn</th>
					<th>Players</th>
					<th>Winrate</th>
				</tr>
			</thead>
			<tbody>
				<!-- Table body will be populated dynamically -->
			</tbody>
		</table>
	</section>
	<footer>Poker Project</footer>
</body>
</html>
