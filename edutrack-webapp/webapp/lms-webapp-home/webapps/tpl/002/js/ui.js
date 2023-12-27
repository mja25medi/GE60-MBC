
/* 
ui 이벤트_추가_230201
 태그에 data-ui, role 속성을 사용한 ui만 해당하며
 안내페이지에서만 사용합니다
*/


function clickUiDataSetHandler(e){   
    
    if( !findMediUiDataSet(e.target) ) return;

    let clickElem = findMediUiDataSet(e.target);
    let mediUi = clickElem.dataset.mediUi;    

    switch( mediUi ){
		case "tabs":
            clickElem.classList.toggle("on");
            document.querySelectorAll(".icon-sort").forEach( (elem, key, par) => {
                elem.classList.toggle("on");
            } );
            break;
        default:
            break;
    }

}

function findMediUiDataSet(elem){
	while( elem.dataset.mediUi === undefined )
	{
		elem = elem.parentNode;
		if( elem === document ){
			return null;
		}
	}
	return elem;
}




class TabsManual {'use strict';
  constructor(groupNode) { 
    this.tablistNode = groupNode;

    this.tabs = [];

    this.firstTab = null;
    this.lastTab = null;

    this.tabs = Array.from(this.tablistNode.querySelectorAll('[role=tab]'));
    this.tabpanels = Array.from(this.tablistNode.querySelectorAll('[role=tabpanel]'));console.log("constructor tab ", this.tabpanels)

    for (var i = 0; i < this.tabs.length; i += 1) {
      var tab = this.tabs[i];
      var tabpanel = document.getElementById(tab.getAttribute('aria-controls'));

      tab.tabIndex = -1;
      tab.setAttribute('aria-selected', 'false');
      tab.setAttribute('aria-controls', `tabpanel-${i+1}`); 
      this.tabpanels[i].setAttribute('id', `tabpanel-${i+1}`);
      //this.tabpanels.push(tabpanel);

      //tab.addEventListener('keydown', this.onKeydown.bind(this));
      tab.addEventListener('click', this.onClick.bind(this));

      if (!this.firstTab) {
        this.firstTab = tab;
      }
      this.lastTab = tab;
    }

    this.setSelectedTab(this.firstTab);
  }

  setSelectedTab(currentTab) {
    for (var i = 0; i < this.tabs.length; i += 1) {
      var tab = this.tabs[i];
      if (currentTab === tab) {
        tab.setAttribute('aria-selected', 'true');
        tab.removeAttribute('tabindex');
        this.tabpanels[i].classList.add('on');
      } else {
        tab.setAttribute('aria-selected', 'false');
        tab.tabIndex = -1;
        this.tabpanels[i].classList.remove('on');
      }
    }
  }

  onClick(event) {
    this.setSelectedTab(event.currentTarget);
  }
}

// Initialize tablist

window.addEventListener('load', function () {
    console.log("load tab")
  var tablists = document.querySelectorAll('.tablist');
  for (var i = 0; i < tablists.length; i++) {
    new TabsManual(tablists[i]);
  }
});

//document.addEventListener("click", clickUiDataSetHandler);