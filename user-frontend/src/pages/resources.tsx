// @flow
import { Resource } from './types';
import { getResourcesList, createResource, deleteResource } from './resources.api';
import { Box, IconButton, Link, makeStyles, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField, Typography } from '@material-ui/core/';
import React, { FormEventHandler, useEffect, useState } from 'react';
import { useNavigate } from '@reach/router';


const useStyles = makeStyles({
    tableRow: {
      cursor: 'pointer'
    },
    textField: {
      marginBottom: '16px',
      marginRight: '16px'
    }
  })



export const Resources = () => {
    const classes = useStyles();
    const [resources, setResources] = useState<Resource[] | null>(null)
    //const navigate = useNavigate();
    

    //const onRowClick = (resourceLink: string) => navigate(resourceLink)
    //onClick={() => onRowClick(resource.resourceLink)}



    useEffect(() => {
        const fetchResourceList = async () => {
          const resourceList = await getResourcesList()
          console.log(resourceList)
          setResources(resourceList)
        }
        fetchResourceList()
      },[])

    return (<>
    <Typography variant="h3">Resources</Typography>
    <TableContainer>
  <Table>
    <TableHead>
      <TableRow>
        <TableCell>ID</TableCell>
        <TableCell>Resource Name</TableCell>
        <TableCell>Resource Link</TableCell>
        <TableCell></TableCell>
      </TableRow>
    </TableHead>
    <TableBody>
      {resources && resources.map(resource => (
        <TableRow className={classes.tableRow} hover key={resource.resourceId} >
          <TableCell>
            {resource.resourceId}
          </TableCell>
          <TableCell>
            {resource.resourceName}
          </TableCell>
          <TableCell>
            {resource.resourceLink}
          </TableCell>
          <TableCell>
          <a href={resource.resourceLink} target="_blank">click here</a>
          </TableCell>
        </TableRow>
      ))}
    </TableBody>
  </Table>
  </TableContainer>
    </>
    );
};