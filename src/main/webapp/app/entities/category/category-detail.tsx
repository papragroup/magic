import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './category.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICategoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CategoryDetail = (props: ICategoryDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { categoryEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoryDetailsHeading">
          <Translate contentKey="magicBodyApp.category.detail.title">Category</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.id}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="magicBodyApp.category.title">Title</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.title}</dd>
          <dt>
            <span id="photoUrl">
              <Translate contentKey="magicBodyApp.category.photoUrl">Photo Url</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.photoUrl}</dd>
          <dt>
            <span id="photo">
              <Translate contentKey="magicBodyApp.category.photo">Photo</Translate>
            </span>
          </dt>
          <dd>
            {categoryEntity.photo ? (
              <div>
                {categoryEntity.photoContentType ? (
                  <a onClick={openFile(categoryEntity.photoContentType, categoryEntity.photo)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {categoryEntity.photoContentType}, {byteSize(categoryEntity.photo)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="voiceUrl">
              <Translate contentKey="magicBodyApp.category.voiceUrl">Voice Url</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.voiceUrl}</dd>
          <dt>
            <span id="voiceFile">
              <Translate contentKey="magicBodyApp.category.voiceFile">Voice File</Translate>
            </span>
          </dt>
          <dd>
            {categoryEntity.voiceFile ? (
              <div>
                {categoryEntity.voiceFileContentType ? (
                  <a onClick={openFile(categoryEntity.voiceFileContentType, categoryEntity.voiceFile)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {categoryEntity.voiceFileContentType}, {byteSize(categoryEntity.voiceFile)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="description">
              <Translate contentKey="magicBodyApp.category.description">Description</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.description}</dd>
          <dt>
            <span id="advice">
              <Translate contentKey="magicBodyApp.category.advice">Advice</Translate>
            </span>
          </dt>
          <dd>{categoryEntity.advice}</dd>
        </dl>
        <Button tag={Link} to="/category" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category/${categoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ category }: IRootState) => ({
  categoryEntity: category.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CategoryDetail);
