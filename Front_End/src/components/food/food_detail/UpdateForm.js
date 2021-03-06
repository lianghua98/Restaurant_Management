import React, { Component } from 'react'

export default class UpdateForm extends Component {
    render() {
        return (
            <div className="modal-dialog modal-lg" role="document">
                <div className="modal-content">
                <div className="modal-header bg-primary">
                    <h2 className="modal-title" id="modifyFoodsTitle" style={{color: 'white'}}>Cập Nhật Thông Tin Món Ăn
                    </h2>
                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div className="modal-body">
                    <form ng-submit="updateFood()">
                    <div className="container">
                        <div className="row">
                        <div className="col-6">
                            <div className="form-group row">
                            <label htmlFor="input1" className="col-sm-4 col-form-label form-control-sm">Mã
                                món ăn:</label>
                            <div className="col-sm-7">
                                <input type="text" className="form-control form-control-sm" id="input1" />
                            </div>
                            </div>
                            <div className="form-group row">
                            <label htmlFor="inputName" className="col-sm-4 col-form-label form-control-sm">Tên món ăn:</label>
                            <div className="col-sm-7">
                                <input type="text" className="form-control form-control-sm" id="inputName" />
                            </div>
                            </div>
                            <div className="form-group row">
                            <label htmlFor="inputType" className="col-sm-4 col-form-label form-control-sm">Loại món ăn:</label>
                            <div className="col-sm-7">
                                <select className="form-control-sm" id="inputType">
                                <option value="{{x.id}}">
                                    {'{'}{'{'}x.loaimonan_NAME{'}'}{'}'}</option>
                                </select>
                            </div>
                            </div>
                            <div className="form-group row">
                            <label htmlFor="inputNum" className="col-sm-4 col-form-label form-control-sm">Giá bán:</label>
                            <div className="col-sm-7">
                                <input type="text" className="form-control form-control-sm " id="inputNum" />
                            </div>
                            </div>
                            <div className="form-group row">
                            <label htmlFor="inputUnit" className="col-sm-4 col-form-label form-control-sm">Đơn vị tính:</label>
                            <div className="col-sm-7">
                                <input type="text" className="form-control form-control-sm " id="inputUnit" />
                            </div>
                            </div>
                            <div className="form-group row">
                            <label htmlFor="inputType" className="col-sm-4 col-form-label form-control-sm">Trạng thái:</label>
                            <div className="col-sm-7">
                                <select className="form-control-sm" id="inputType">
                                <option value="Còn">
                                    Còn
                                </option>
                                <option value="Không">
                                    Không
                                </option>
                                </select>
                            </div>
                            </div>
                        </div>
                        <div className="col-6">
                            <div className="col-6">
                            <label htmlFor="inputNum" className="col-sm-4 col-form-label form-control-sm">Hình Ảnh:</label>
                            <div className="container" ng-controller="UploadController">
                                <div className="row">
                                <div className="card-body border">
                                    <div className="col-6">
                                    <img src="assets/img/resources/Mon_an_va_nuoc/{{x.monan_IMG}}" width={150} height={150} value="assets/img/resources/Mon_an_va_nuoc/{{x.monan_IMG}}" alt="picture" />
                                    </div>
                                </div>
                                <div className="col-6" />
                                </div>
                                <div className="row mt-1">
                                <div className="file-field">
                                    <div className="btn form-control-file btn-sm btn-success ml-2">
                                    <input type="file" />
                                    </div>
                                </div>
                                </div>
                            </div>
                            </div>
                        </div>
                        </div>
                    </div>
                    <div className="text-right mt-3">
                        <button type="submit" className="btn btn-danger">Lưu</button>
                        <button type="button" className="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                    </form>
                </div>
                </div>
            </div>
        )
    }
}
