    var preFrameW = '206,*';
	var FrameHide = 0;
	var curStyle = 1;
	var totalItem = 9;
    
	function ChangeMenu(way) {
		var buttomFrame=top.document.getElementById('btFrame');
		var addwidth = 10;
		var fcol = buttomFrame.cols;
		if (way == 1)
			addwidth = 10;
		else if (way == -1)
			addwidth = -10;
		else if (way == 0) {
			if (FrameHide == 0) {
				preFrameW = buttomFrame.cols;
				buttomFrame.cols = '0,*';
				FrameHide = 1;
				return;
			} else {
				buttomFrame.cols = preFrameW;
				FrameHide = 0;
				return;
			}
		}
		fcols = fcol.split(',');
		fcols[0] = parseInt(fcols[0]) + addwidth;
		buttomFrame.cols = fcols[0] + ',*';
	}

	function mv(selobj, moveout, itemnum) {
		if (itemnum == curStyle)
			return false;
		if (moveout == 'm')
			selobj.className = 'itemsel';
		if (moveout == 'o')
			selobj.className = 'item';
		return true;
	}

	function changeSel(itemnum) {
		curStyle = itemnum;
		for (i = 1; i <= totalItem; i++) {
			if (document.getElementById('item' + i))
				document.getElementById('item' + i).className = 'item';
		}
		document.getElementById('item' + itemnum).className = 'itemsel';
	}