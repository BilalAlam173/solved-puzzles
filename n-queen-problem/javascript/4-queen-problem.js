console.log('started');

var queensPosition=[
    {
    row:'',
    col:''
},
{
    row:'',
    col:''
},
{
    row:'',
    col:''
},
{
    row:'',
    col:''
}];

var rowFirstQueen=0;
var colFirstQueen=0;
var globalcounter=0;

function map(){
    var board=[
        [0,0,0,0],
        [0,0,0,0],
        [0,0,0,0],
        [0,0,0,0]
        ];

    board[rowFirstQueen][colFirstQueen]=1;
    queensPosition[0].row=rowFirstQueen;
    queensPosition[0].col=colFirstQueen;
    var found=false;

    for( var queen=1;queen<4;queen++){
        found=false;
        queen:
        for(var i=rowFirstQueen;i<4;i++){
            for(var j=colFirstQueen+1;j<4;j++){
                if(checkAttack(i,j)){
                    console.log("placing",i+","+j);
                    board[i][j]=1;
                    queensPosition[queen].row=i;
                    queensPosition[queen].row=j;
                    found=true;
                    break queen;
                }
            }
        }
    }
        if(!found){
            console.log('not found');
            if(colFirstQueen<3){
                colFirstQueen++;
            }else{
                colFirstQueen=0;
                rowFirstQueen++;
            }
            if(rowFirstQueen<4&&colFirstQueen<4){
            map();
            }
        }else{
            console.log('solved',board);
        }
        console.log(board);
}

function checkAttack(i,j){
    var returnValue=true;
    queensPosition.forEach(function(queen){
        //same row
        if(queen.row){
        if(queen.row==i||queen.col==j){
            returnValue= false;
        }else{
            returnValue=diagonal(queen,i,j);
        }
    }
    });
    if(i===1&&j===3&&queensPosition[0].row===0&&queensPosition[0].col===0){
        console.log(JSON.stringify(queensPosition));
    }
    if(returnValue===true){
        
    }
    return returnValue;
}

function diagonal(queen,row,col){
    var count=1;
    var returnValue=true;
    
    //downward right
    while(count<4){
    for(var i=queen.row+count;i<4;i++){
        for(var j=queen.col+count;j<4;j++){
               if(i==row||j==col){
                returnValue= false;
            }
        }
    }
count++;
}

count=1;
//downward left
while(count<4){
for(var i=queen.row+count;i<4;i++){
    for(var j=queen.col-count;j>=0;j--){
        if(i==row||j==col){
            returnValue= false;
        }
    }
}
count++;
}

count=1;
//upward left
while(count<4){
for(var i=queen.row-count;i>=4;i--){
    for(var j=queen.col-count;j>=0;j--){
        if(i==row||j==col){
            returnValue= false;
        }
    }
}
count++;
}

count=1;
//upward right
while(count<4){
for(var i=queen.row-count;i>=4;i--){
    for(var j=queen.col+count;j<4;j++){
        if(i==row||j==col){
            returnValue= false;
        }
    }
}
count++;
}
if(row===1&&col===3&&queensPosition[0].row===0&&queensPosition[0].col===0){
    console.log(JSON.stringify(returnValue));
}
return returnValue;
}

map();
