/* eslint-disable no-undef */

import React, { Component } from 'react'
import PropTypes from 'prop-types'

export default class List extends Component {
  static propTypes = {
    loadingLabel: PropTypes.string.isRequired,
    pageCount: PropTypes.number,
    renderItem: PropTypes.func.isRequired,
    items: PropTypes.object.isRequired,
    isFetching: PropTypes.bool.isRequired,
    nextPageUrl: PropTypes.string
  }

  static defaultProps = {
    isFetching: true,
    loadingLabel: 'Loading...'
  }

  renderLoadMore() {
    const { isFetching, onLoadMoreClick } = this.props
    return (
      <button style={{ fontSize: '150%' }}
              onClick={onLoadMoreClick}
              disabled={isFetching}>
        {isFetching ? 'Loading...' : 'Load More'}
      </button>
    )
  }

  render() {
    const {
      isFetching, nextPageUrl, pageCount,
      items, renderItem, loadingLabel
    } = this.props
    const isEmpty = items == null || Object.keys(items).length === 0
    if (isEmpty && isFetching) {
      return <h2><i>{loadingLabel}</i></h2>
    }

    const isLastPage = !nextPageUrl
    if (isEmpty && isLastPage) {
      return <h1><i>Nothing here!</i></h1>
    }

    return (
      <div>
        {Object.keys(items).map((id) => renderItem(items[id]))}
        {pageCount > 0 && !isLastPage && this.renderLoadMore()}
      </div>
    )
  }
}
