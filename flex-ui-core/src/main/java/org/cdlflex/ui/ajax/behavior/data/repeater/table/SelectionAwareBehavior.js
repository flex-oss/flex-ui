/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
/**
 * Tools for selection aware widgets.
 */
(function ($) {

    if (!$.widget) {
        console.error("jquery-ui widget factory is not defined");
        return;
    }

    /**
     * Used by SelectionAwareBehavior.
     */
    $.widget("flex.selectable", {
        options: {
            selectionClass: "selected",
            itemSelector: "tr"
        },
        _create: function () {
            this.element.addClass("selectable");

            var items = $(this.options.itemSelector, this.element);
            var $this = this;

            for (var i = 0; i < items.length; i++) {
                if ($(items[i]).hasClass($this.options.selectionClass)) {
                    $this.selected = $(items[i]);
                    break;
                }
            }

            items.on('click', function (event) {
                $this.onClick($(this), event);
            });
        },
        onClick: function (item, event) {
            var prev = this.selected;

            if (prev && prev[0] == item[0]) {
                return;
            }
            if (prev) {
                prev.removeClass(this.options.selectionClass);
            }

            item.addClass(this.options.selectionClass);
            item.trigger("select");

            this.selected = item;
        }
    });

    /**
     * Used by ToggleAwareBehavior.
     */
    $.widget("flex.toggleable", $.flex.selectable, {
        onClick: function (item, event) {
            var prev = this.selected;

            if (prev && prev[0] == item[0]) {
                prev.removeClass(this.options.selectionClass);
                this.selected = null;
                item.trigger("deselect");
                return;
            }
            if (prev) {
                prev.removeClass(this.options.selectionClass);
            }

            item.addClass(this.options.selectionClass);
            item.trigger("select");

            this.selected = item;
        }
    });

    /**
     * Used by MultiSelectionAwareBehavior.
     */
    $.widget("flex.multiselectable", $.flex.selectable, {
        _create: function () {
            this._super();
            this.selected = [];
        },
        onClick: function (item, event) {
            var i = $.inArray(item[0], this.selected);

            if (i == -1) {
                this.selected.push(item[0]);
                item.addClass(this.options.selectionClass);
                item.trigger("select");
            } else {
                this.selected.splice(i, 1);
                item.removeClass(this.options.selectionClass);
                item.trigger("deselect");
            }
        }
    });

})(jQuery);
