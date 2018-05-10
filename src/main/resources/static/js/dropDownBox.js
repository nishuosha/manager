var DropDownBox = function({
                           id,
                           dataSource,
                           pageSize = 15,
                           width = 300
                       }) {
    this.id = id;
    this.width = width
    //数据
    this.cached = dataSource;
    this.dataSource = dataSource;
    this.dataList = [];

    //分页数据
    this.page = 1;
    this.pageSize = pageSize;
    this.pageCount = Math.ceil(dataSource.length / pageSize);

    // dom
    this.inputEle = document.getElementById(id);
    this.listWrap = null;
    this.list = null;
    this.pageLocation = null;

    // input聚焦事件监听,初始化dom
    this._addEventFocus()
    this._addEventOnchange()

    this.timer = null;
}
DropDownBox.prototype = {
    constructor: DropDownBox,
    _initDom: function() {
        if (this.listWrap) {
            this._adjustPosition()
            this.listWrap.style.display = 'block'
            // 更新数据
        } else {
            this._updataPageAndData(1);
            var wrap = this._initWrapper()
            var listwrap = this._initContentWrapper()
            var list = this._generateList();
            var pageLocation = this._generatePageLocation();
            this._adjustPosition()
            listwrap.appendChild(list);
            listwrap.appendChild(pageLocation);
            wrap.appendChild(listwrap);
            document.body.appendChild(wrap);
            // 只需执行一次监听事件
            this._addEventBlur()
            this._addEventItemClick()
            this._addEventPageClick()
        }
    },
    _initWrapper: function() {
        var wrap = document.createElement('div')
        wrap.style.position = 'absolute';
        wrap.style.top = '0px';
        wrap.style.left = '0px';
        wrap.style.width = '100%';
        return wrap;
    },
    _initContentWrapper: function() {
        var listWrap = document.createElement('div')
        listWrap.setAttribute('class', 'selectListWrap')
        listWrap.style.position = 'absolute';
        listWrap.style.width = this.width + 'px';
        this.listWrap = listWrap
        return listWrap;
    },
    _generateLi: function(text) {
        var li = document.createElement('li')
        li.innerHTML = text
        return li;
    },
    _generateList: function() {
        var list = document.createElement('ul');
        list.setAttribute('class', 'selectList')
        this.list = list;
        this._generateDataItems()
        return list;
    },
    _generateDataItems: function() {
        var _this = this;
        this.dataList.map(function(text) {
            _this.list.appendChild(_this._generateLi(text))
        })
    },
    _generatePageLocation: function() {
        var pageLocation = document.createElement('ul');
        pageLocation.setAttribute('class', 'selectPageLocation')
        this.pageLocation = pageLocation;
        this._generatePagerItems()
        return pageLocation;
    },
    _generatePagerItems: function() {
        for (var i = 0; i < this.pageCount; i++) {
            var pager = this._generateLi(i + 1 + '');
            if (i === 0) {
                pager.setAttribute('class', 'pageActive')
            }
            this.pageLocation.appendChild(pager)
        }
    },
    _adjustPosition: function() {
        var top = this.inputEle.offsetTop
        var left = this.inputEle.offsetLeft
        var h = this.inputEle.offsetHeight
        this.listWrap.style.top = top + h + 5 + 'px'
        this.listWrap.style.left = left + 'px'
    },
    _updataPageAndData: function(page) {
        this.page = page;
        var pageStart = (page - 1) * this.pageSize;
        var pageEnd = page * this.pageSize;
        this.dataList = this.dataSource.slice(pageStart, pageEnd);
    },
    _addEventFocus: function() {
        var _this = this;
        _this.inputEle.addEventListener('focus', function() {
            _this._initDom();
        })
    },
    _addEventBlur: function() {
        var _this = this;
        document.addEventListener('click', function(e) {
            var target = e.target;
            if (!_this.listWrap.contains(target) &&
                target !== _this.inputEle
            ) {
                _this.listWrap.style.display = 'none'
            }
        })
    },
    _addEventItemClick: function() {
        var _this = this;
        this.list.addEventListener('click', function(e) {
            var target = e.target;
            if (target.tagName.toLocaleLowerCase() === 'li') {
                _this.inputEle.value = target.innerHTML
                _this.listWrap.style.display = 'none'
            }
        })
    },
    _addEventPageClick: function() {
        var _this = this;
        this.pageLocation.addEventListener('click', function(e) {
            var target = e.target;
            if (
                target.tagName.toLocaleLowerCase() === 'li' &&
                parseInt(target.innerHTML) !== _this.page
            ) {
                _this._changePageActive(target)
                _this._updataPageAndData(parseInt(target.innerHTML))
                _this._updateList()
            }
        })
    },
    _addEventOnchange: function() {
        var _this = this;
        this.inputEle.addEventListener('input', function(e) {
            var display = _this.listWrap.style;
            var value = e.target.value;
            if(display !== 'none') {
                window.clearTimeout(this.timer)
                this.timer = setTimeout(function() {
                    var filterData = _this.cached.filter(function(item){
                        return item.includes(value)
                    })
                    _this.changeDataSource(filterData);
                }, 500)
            }
        })
    },
    _changePageActive: function(pager) {
        this.pageLocation.getElementsByClassName('pageActive')[0].setAttribute('class', '')
        pager.setAttribute('class', 'pageActive')
    },
    _updateList: function() {
        this.list.innerHTML = null;
        this._generateDataItems()
    },
    _updatePageLocation: function() {
        this.pageCount = Math.ceil(this.dataSource.length / this.pageSize);
        this.pageLocation.innerHTML = null;
        this._generatePagerItems();
    },
    changeDataSource: function(dataSource) {
        this.dataSource = dataSource;
        this._updataPageAndData(1);
        this._updateList()
        this._updatePageLocation()
    }
}