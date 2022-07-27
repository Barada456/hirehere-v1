const dragstart = event => {
                let text = event.srcElement.textContent;
                event.dataTransfer.setData("text", text);
                console.log(text);
            };
            const drop = event => {
                event.preventDefault();
                let text = event.dataTransfer.getData('text');
                let destList = document.querySelector('.dest-list');
                if (destList) {
                    let item = document.createElement('li');
                    item.textContent = text;
                    destList.appendChild(item);
                }
            };
            const dragover = event => {
                event.preventDefault();
            };
            function myFunction() {
                console.log("clicked");
                document.getElementById("des").innerHTML = '';
            }